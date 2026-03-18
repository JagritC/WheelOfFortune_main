import { useState } from 'react';
import type { EngineState } from '../../lib/gameEngine';
import './ActionPanel.css';

const CONSONANTS = 'BCDFGHJKLMNPQRSTVWXYZ'.split('');
const VOWELS = 'AEIOU'.split('');
const VOWEL_COST = 500;

interface Props {
  state: EngineState;
  onSpin: () => void;
  onGuessConsonant: (letter: string) => void;
  onBuyVowel: (letter: string) => void;
  onSolve: (answer: string) => void;
}

export function ActionPanel({
  state,
  onSpin,
  onGuessConsonant,
  onBuyVowel,
  onSolve,
}: Props) {
  const [selectedConsonant, setSelectedConsonant] = useState('B');
  const [selectedVowel, setSelectedVowel] = useState('A');
  const [solveInput, setSolveInput] = useState('');
  const [showSolve, setShowSolve] = useState(false);
  const [activeTab, setActiveTab] = useState<'consonant' | 'vowel'>('consonant');

  const { phase, guessedLetters, players, currentPlayerIndex } = state;
  const currentPlayer = players[currentPlayerIndex];
  const canAffordVowel = currentPlayer.roundBalance >= VOWEL_COST;

  const availableConsonants = CONSONANTS.filter(l => !guessedLetters.has(l));
  const availableVowels = VOWELS.filter(l => !guessedLetters.has(l));

  // Keep selection valid when letters get used up
  const effectiveConsonant =
    availableConsonants.includes(selectedConsonant)
      ? selectedConsonant
      : availableConsonants[0] ?? '';
  const effectiveVowel =
    availableVowels.includes(selectedVowel)
      ? selectedVowel
      : availableVowels[0] ?? '';

  function handleSolve() {
    if (solveInput.trim()) {
      onSolve(solveInput.trim());
      setSolveInput('');
      setShowSolve(false);
    }
  }

  const isSpinning = phase === 'spinning';
  const canSpin = phase === 'waiting-to-spin';
  const canGuessConsonant = phase === 'choose-action';
  // Vowels can be bought any time on your turn (no spin required — fixed $500 cost)
  const canBuyVowel = (phase === 'waiting-to-spin' || phase === 'choose-action') && canAffordVowel;
  const canSolve = phase === 'waiting-to-spin' || phase === 'choose-action';
  const isGameOver = phase === 'game-over' || phase === 'round-over';

  if (isGameOver) return null;

  return (
    <div className="action-panel">
      {/* Status message */}
      <div className={`status-message ${phase === 'spinning' ? 'pulsing' : ''}`}>
        {state.message}
      </div>

      {/* Solve overlay */}
      {showSolve && (
        <div className="solve-overlay">
          <div className="solve-box">
            <div className="solve-title">Solve the Puzzle</div>
            <input
              className="solve-input"
              value={solveInput}
              onChange={e => setSolveInput(e.target.value.toUpperCase())}
              onKeyDown={e => e.key === 'Enter' && handleSolve()}
              placeholder="Type your answer…"
              autoFocus
              maxLength={80}
            />
            <div className="solve-buttons">
              <button className="btn btn-primary" onClick={handleSolve} disabled={!solveInput.trim()}>
                Submit
              </button>
              <button className="btn btn-ghost" onClick={() => { setShowSolve(false); setSolveInput(''); }}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      <div className="action-row">
        {/* Spin button */}
        <button
          className={`btn btn-spin ${isSpinning ? 'spinning' : ''}`}
          onClick={onSpin}
          disabled={!canSpin || isSpinning}
        >
          {isSpinning ? 'Spinning…' : 'SPIN'}
        </button>

        {/* Letter guessing area */}
        <div className="guess-area">
          <div className="guess-tabs">
            <button
              className={`tab-btn ${activeTab === 'consonant' ? 'active' : ''}`}
              onClick={() => setActiveTab('consonant')}
            >
              Consonant
            </button>
            <button
              className={`tab-btn ${activeTab === 'vowel' ? 'active' : ''}`}
              onClick={() => setActiveTab('vowel')}
            >
              Vowel <span className="cost-badge">$500</span>
            </button>
          </div>

          {activeTab === 'consonant' ? (
            <div className="letter-row">
              <div className="letter-grid consonant-grid">
                {CONSONANTS.map(l => {
                  const used = guessedLetters.has(l);
                  const selected = effectiveConsonant === l;
                  return (
                    <button
                      key={l}
                      className={`letter-btn ${used ? 'used' : ''} ${selected && !used ? 'selected' : ''}`}
                      onClick={() => !used && setSelectedConsonant(l)}
                      disabled={used}
                    >
                      {l}
                    </button>
                  );
                })}
              </div>
              <button
                className="btn btn-primary"
                onClick={() => onGuessConsonant(effectiveConsonant)}
                disabled={!canGuessConsonant || !effectiveConsonant}
              >
                Guess {effectiveConsonant}
              </button>
            </div>
          ) : (
            <div className="letter-row">
              <div className="letter-grid vowel-grid">
                {VOWELS.map(l => {
                  const used = guessedLetters.has(l);
                  const selected = effectiveVowel === l;
                  return (
                    <button
                      key={l}
                      className={`letter-btn ${used ? 'used' : ''} ${selected && !used ? 'selected' : ''}`}
                      onClick={() => !used && setSelectedVowel(l)}
                      disabled={used}
                    >
                      {l}
                    </button>
                  );
                })}
              </div>
              <button
                className="btn btn-secondary"
                onClick={() => onBuyVowel(effectiveVowel)}
                disabled={!canBuyVowel || !effectiveVowel}
                title={!canAffordVowel ? `Need $${VOWEL_COST} to buy a vowel` : ''}
              >
                Buy {effectiveVowel}
              </button>
            </div>
          )}
        </div>

        {/* Solve button */}
        <button
          className="btn btn-solve"
          onClick={() => setShowSolve(true)}
          disabled={!canSolve}
        >
          SOLVE
        </button>
      </div>

      {/* Used letters */}
      {guessedLetters.size > 0 && (
        <div className="used-letters">
          <span className="used-label">Used: </span>
          {[...guessedLetters].sort().map(l => (
            <span key={l} className="used-letter">{l}</span>
          ))}
        </div>
      )}
    </div>
  );
}
