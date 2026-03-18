import './MainMenu.css';

interface Props {
  onPlay: () => void;
  onLeaderboard: () => void;
}

export function MainMenu({ onPlay, onLeaderboard }: Props) {
  return (
    <div className="main-menu">
      <div className="menu-content">
        <div className="title-block">
          <div className="title-wheel">
            <svg width="80" height="80" viewBox="0 0 80 80">
              {Array.from({ length: 8 }).map((_, i) => {
                const angle = (i * 360) / 8;
                const colors = ['#e74c3c','#f39c12','#27ae60','#2980b9','#8e44ad','#16a085','#d35400','#f1c40f'];
                const rad = ((angle - 90) * Math.PI) / 180;
                const rad2 = (((angle + 45) - 90) * Math.PI) / 180;
                const r = 36;
                const x1 = 40 + r * Math.cos(rad);
                const y1 = 40 + r * Math.sin(rad);
                const x2 = 40 + r * Math.cos(rad2);
                const y2 = 40 + r * Math.sin(rad2);
                return (
                  <path
                    key={i}
                    d={`M 40 40 L ${x1} ${y1} A ${r} ${r} 0 0 1 ${x2} ${y2} Z`}
                    fill={colors[i]}
                    stroke="#1a1a2e"
                    strokeWidth={1.5}
                  />
                );
              })}
              <circle cx="40" cy="40" r="10" fill="#1a1a2e" stroke="#f1c40f" strokeWidth="2" />
              <circle cx="40" cy="40" r="4" fill="#f1c40f" />
            </svg>
          </div>
          <h1 className="game-title">
            <span className="title-wheel-text">Wheel</span>
            <span className="title-of">of</span>
            <span className="title-fortune">Fortune</span>
          </h1>
          <p className="tagline">Spin. Guess. Win.</p>
        </div>

        <div className="menu-buttons">
          <button className="menu-btn primary" onClick={onPlay}>
            <span className="btn-icon">▶</span>
            Play Game
          </button>
          <button className="menu-btn secondary" onClick={onLeaderboard}>
            <span className="btn-icon">🏆</span>
            Leaderboard
          </button>
        </div>

        <div className="menu-rules">
          <div className="rules-title">How to Play</div>
          <ul className="rules-list">
            <li>Spin the wheel to reveal a dollar amount</li>
            <li>Guess a consonant — earn that amount for each letter found</li>
            <li>Or buy a vowel for $500 (no spin needed)</li>
            <li>Land on <strong>BANKRUPT</strong> and lose your round balance</li>
            <li>Land on <strong>LOSE A TURN</strong> and your turn passes</li>
            <li>Solve the puzzle to bank your round earnings</li>
            <li>Highest total after 3 rounds wins</li>
          </ul>
        </div>
      </div>
    </div>
  );
}
