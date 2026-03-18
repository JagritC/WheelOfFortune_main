import { useState } from 'react';
import { MainMenu } from './components/screens/MainMenu';
import { PlayerSetup } from './components/screens/PlayerSetup';
import { GameScreen } from './components/screens/GameScreen';
import { LeaderboardScreen } from './components/screens/LeaderboardScreen';

type Screen = 'menu' | 'setup' | 'game' | 'leaderboard';

interface GamePlayers {
  p1: string;
  p2: string;
}

export function App() {
  const [screen, setScreen] = useState<Screen>('menu');
  const [players, setPlayers] = useState<GamePlayers>({ p1: '', p2: '' });

  function startSetup() {
    setScreen('setup');
  }

  function startGame(p1: string, p2: string) {
    setPlayers({ p1, p2 });
    setScreen('game');
  }

  function goToMenu() {
    setScreen('menu');
  }

  function goToLeaderboard() {
    setScreen('leaderboard');
  }

  switch (screen) {
    case 'menu':
      return <MainMenu onPlay={startSetup} onLeaderboard={goToLeaderboard} />;

    case 'setup':
      return <PlayerSetup onStart={startGame} onBack={goToMenu} />;

    case 'game':
      return (
        <GameScreen
          player1Name={players.p1}
          player2Name={players.p2}
          onGameOver={goToLeaderboard}
        />
      );

    case 'leaderboard':
      return <LeaderboardScreen onBack={goToMenu} />;
  }
}

export default App;
