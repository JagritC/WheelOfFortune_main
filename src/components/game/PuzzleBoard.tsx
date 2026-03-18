import { visibleLetters } from '../../lib/gameEngine';
import type { GameState } from '../../lib/types';
import './PuzzleBoard.css';

interface Props {
  state: GameState;
}

export function PuzzleBoard({ state }: Props) {
  const visible = visibleLetters(state);
  const words = state.puzzle.puzzle.split(' ');

  return (
    <div className="puzzle-board">
      <div className="puzzle-category">
        <span className="category-label">{state.puzzle.category}</span>
        <span className="hint-label">Hint: {state.puzzle.hint}</span>
      </div>

      <div className="puzzle-words">
        {words.map((word, wi) => (
          <div key={wi} className="puzzle-word">
            {[...word].map((ch, ci) => {
              const isRevealed = visible.has(ch);
              return (
                <div
                  key={ci}
                  className={`letter-tile ${isRevealed ? 'revealed' : 'hidden'}`}
                >
                  <span className="letter-char">{isRevealed ? ch : ''}</span>
                </div>
              );
            })}
          </div>
        ))}
      </div>
    </div>
  );
}
