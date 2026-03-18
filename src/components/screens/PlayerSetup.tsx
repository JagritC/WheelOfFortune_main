import { useState } from 'react';
import './PlayerSetup.css';

interface Props {
  onStart: (player1: string, player2: string) => void;
  onBack: () => void;
}

export function PlayerSetup({ onStart, onBack }: Props) {
  const [p1, setP1] = useState('');
  const [p2, setP2] = useState('');
  const [errors, setErrors] = useState({ p1: '', p2: '' });

  function validate() {
    const e = { p1: '', p2: '' };
    if (!p1.trim()) e.p1 = 'Name required';
    if (!p2.trim()) e.p2 = 'Name required';
    if (p1.trim() && p2.trim() && p1.trim().toLowerCase() === p2.trim().toLowerCase())
      e.p2 = 'Players must have different names';
    setErrors(e);
    return !e.p1 && !e.p2;
  }

  function handleStart() {
    if (validate()) onStart(p1.trim(), p2.trim());
  }

  return (
    <div className="player-setup">
      <div className="setup-card">
        <button className="back-btn" onClick={onBack}>← Back</button>
        <h2 className="setup-title">Enter Player Names</h2>
        <p className="setup-subtitle">Two players — one wheel</p>

        <div className="player-inputs">
          <div className="player-input-group">
            <label className="player-label">
              <span className="player-badge p1">P1</span>
              Player 1
            </label>
            <input
              className={`player-input ${errors.p1 ? 'error' : ''}`}
              value={p1}
              onChange={e => setP1(e.target.value)}
              onKeyDown={e => e.key === 'Enter' && handleStart()}
              placeholder="Enter name…"
              maxLength={20}
              autoFocus
            />
            {errors.p1 && <div className="input-error">{errors.p1}</div>}
          </div>

          <div className="vs-divider">VS</div>

          <div className="player-input-group">
            <label className="player-label">
              <span className="player-badge p2">P2</span>
              Player 2
            </label>
            <input
              className={`player-input ${errors.p2 ? 'error' : ''}`}
              value={p2}
              onChange={e => setP2(e.target.value)}
              onKeyDown={e => e.key === 'Enter' && handleStart()}
              placeholder="Enter name…"
              maxLength={20}
            />
            {errors.p2 && <div className="input-error">{errors.p2}</div>}
          </div>
        </div>

        <button className="start-btn" onClick={handleStart}>
          Start Game
        </button>
      </div>
    </div>
  );
}
