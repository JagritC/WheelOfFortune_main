import type { Player } from '../../lib/types';
import './PlayerPanel.css';

interface Props {
  player: Player;
  isActive: boolean;
}

export function PlayerPanel({ player, isActive }: Props) {
  return (
    <div className={`player-panel ${isActive ? 'active' : 'inactive'}`}>
      {isActive && <div className="active-indicator">▶ YOUR TURN</div>}
      <div className="player-name">{player.name}</div>
      <div className="balance-row">
        <div className="balance-block">
          <div className="balance-label">Round</div>
          <div className="balance-value round-bal">
            ${player.roundBalance.toLocaleString()}
          </div>
        </div>
        <div className="balance-divider" />
        <div className="balance-block">
          <div className="balance-label">Total</div>
          <div className="balance-value total-bal">
            ${player.totalBalance.toLocaleString()}
          </div>
        </div>
      </div>
    </div>
  );
}
