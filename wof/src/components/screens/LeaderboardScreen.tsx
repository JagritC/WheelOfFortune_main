import { useState } from 'react';
import { loadLeaderboard, clearLeaderboard } from '../../lib/leaderboard';
import './LeaderboardScreen.css';

interface Props {
  onBack: () => void;
}

export function LeaderboardScreen({ onBack }: Props) {
  const [entries, setEntries] = useState(() => loadLeaderboard());

  function handleClear() {
    if (confirm('Clear all leaderboard entries?')) {
      clearLeaderboard();
      setEntries([]);
    }
  }

  return (
    <div className="leaderboard-screen">
      <div className="lb-card">
        <button className="back-btn" onClick={onBack}>← Back</button>
        <h2 className="lb-title">🏆 Leaderboard</h2>

        {entries.length === 0 ? (
          <div className="lb-empty">No scores yet — play a game to appear here!</div>
        ) : (
          <div className="lb-table">
            <div className="lb-header">
              <span className="col-rank">#</span>
              <span className="col-name">Player</span>
              <span className="col-score">Total Won</span>
              <span className="col-date">Date</span>
            </div>
            {entries.map((entry, i) => (
              <div key={i} className={`lb-row ${i < 3 ? `top-${i + 1}` : ''}`}>
                <span className="col-rank">
                  {i === 0 ? '🥇' : i === 1 ? '🥈' : i === 2 ? '🥉' : i + 1}
                </span>
                <span className="col-name">{entry.name}</span>
                <span className="col-score">${entry.score.toLocaleString()}</span>
                <span className="col-date">
                  {new Date(entry.date).toLocaleDateString(undefined, {
                    month: 'short',
                    day: 'numeric',
                    year: 'numeric',
                  })}
                </span>
              </div>
            ))}
          </div>
        )}

        {entries.length > 0 && (
          <button className="clear-btn" onClick={handleClear}>
            Clear All
          </button>
        )}
      </div>
    </div>
  );
}
