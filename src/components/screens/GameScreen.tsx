import { useState, useCallback } from 'react';
import {
  createInitialState,
  doSpin,
  doResolveSpin,
  doGuessConsonant,
  doBuyVowel,
  doSolveAttempt,
  doContinueToNextRound,
  getWinner,
  pickPuzzles,
  type EngineState,
} from '../../lib/gameEngine';
import { addEntries } from '../../lib/leaderboard';
import { PuzzleBoard } from '../game/PuzzleBoard';
import { SpinWheel } from '../game/SpinWheel';
import { PlayerPanel } from '../game/PlayerPanel';
import { ActionPanel } from '../game/ActionPanel';
import './GameScreen.css';

const TOTAL_ROUNDS = 3;

interface Props {
  player1Name: string;
  player2Name: string;
  onGameOver: () => void;
}

export function GameScreen({ player1Name, player2Name, onGameOver }: Props) {
  const [state, setState] = useState<EngineState>(() => {
    const puzzles = pickPuzzles(TOTAL_ROUNDS);
    return createInitialState(player1Name, player2Name, puzzles, TOTAL_ROUNDS);
  });

  // ─── Spin handlers ─────────────────────────────────────────────────────────
  const handleSpin = useCallback(() => {
    setState(s => doSpin(s));
  }, []);

  const handleSpinComplete = useCallback(() => {
    setState(s => doResolveSpin(s as EngineState & { _spinTargetIndex: number }));
  }, []);

  // ─── Action handlers ───────────────────────────────────────────────────────
  const handleGuessConsonant = useCallback((letter: string) => {
    try {
      setState(s => doGuessConsonant(s, letter));
    } catch (e) {
      console.warn(e);
    }
  }, []);

  const handleBuyVowel = useCallback((letter: string) => {
    try {
      setState(s => doBuyVowel(s, letter));
    } catch (e) {
      console.warn(e);
    }
  }, []);

  const handleSolve = useCallback((answer: string) => {
    setState(s => doSolveAttempt(s, answer));
  }, []);

  const handleContinue = useCallback(() => {
    setState(s => doContinueToNextRound(s));
  }, []);

  const handleGameOverContinue = useCallback(() => {
    const entries = state.players.map(p => ({ name: p.name, score: p.totalBalance }));
    addEntries(entries);
    onGameOver();
  }, [state, onGameOver]);

  // ─── Round-over overlay ────────────────────────────────────────────────────
  const showRoundOver = state.phase === 'round-over';
  const showGameOver = state.phase === 'game-over';

  const winner = showGameOver ? getWinner(state) : null;
  const [p1, p2] = state.players;

  return (
    <div className="game-screen">
      {/* ── Round badge ── */}
      <div className="round-badge">
        Round {state.phase === 'round-over' ? state.round - 1 : state.round} / {TOTAL_ROUNDS}
      </div>

      {/* ── Player panels ── */}
      <div className="players-row">
        <PlayerPanel player={p1} isActive={state.currentPlayerIndex === 0} />
        <PlayerPanel player={p2} isActive={state.currentPlayerIndex === 1} />
      </div>

      {/* ── Main play area ── */}
      <div className="play-area">
        <div className="wheel-col">
          <SpinWheel
            spinning={state.phase === 'spinning'}
            targetIndex={(state as EngineState & { _spinTargetIndex: number })._spinTargetIndex ?? 0}
            onSpinComplete={handleSpinComplete}
          />
          {state.lastSpinResult && state.phase !== 'spinning' && (
            <div className={`spin-result ${state.lastSpinResult.kind}`}>
              {state.lastSpinResult.label}
            </div>
          )}
        </div>

        <div className="board-col">
          <PuzzleBoard state={state} />
          <ActionPanel
            state={state}
            onSpin={handleSpin}
            onGuessConsonant={handleGuessConsonant}
            onBuyVowel={handleBuyVowel}
            onSolve={handleSolve}
          />
        </div>
      </div>

      {/* ── Round-over overlay ── */}
      {showRoundOver && (
        <div className="overlay">
          <div className="overlay-card round-over-card">
            <div className="overlay-emoji">🎉</div>
            <div className="overlay-title">Round {state.round - 1} Complete!</div>
            <div className="overlay-body">{state.message}</div>
            <div className="score-summary">
              {state.players.map(p => (
                <div key={p.id} className="score-row">
                  <span className="score-name">{p.name}</span>
                  <span className="score-total">${p.totalBalance.toLocaleString()}</span>
                </div>
              ))}
            </div>
            <button className="overlay-btn" onClick={handleContinue}>
              Round {state.round} →
            </button>
          </div>
        </div>
      )}

      {/* ── Game-over overlay ── */}
      {showGameOver && (
        <div className="overlay">
          <div className="overlay-card game-over-card">
            <div className="overlay-emoji">{winner ? '🏆' : '🤝'}</div>
            <div className="overlay-title">
              {winner ? `${winner.name} Wins!` : "It's a Tie!"}
            </div>
            <div className="overlay-body">
              {winner
                ? `Congratulations! ${winner.name} wins with $${winner.totalBalance.toLocaleString()}`
                : `Both players finished with $${p1.totalBalance.toLocaleString()} — incredible!`}
            </div>
            <div className="score-summary">
              {[...state.players]
                .sort((a, b) => b.totalBalance - a.totalBalance)
                .map((p, i) => (
                  <div key={p.id} className={`score-row ${i === 0 && winner ? 'winner-row' : ''}`}>
                    <span className="score-name">
                      {i === 0 && winner ? '🥇 ' : ''}{p.name}
                    </span>
                    <span className="score-total">${p.totalBalance.toLocaleString()}</span>
                  </div>
                ))}
            </div>
            <button className="overlay-btn" onClick={handleGameOverContinue}>
              Main Menu
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
