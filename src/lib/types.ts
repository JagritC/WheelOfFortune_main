// ─── Puzzle ──────────────────────────────────────────────────────────────────

export interface Puzzle {
  puzzle: string;   // uppercase, spaces already included
  category: string;
  hint: string;
}

// ─── Wheel ───────────────────────────────────────────────────────────────────

export type WedgeKind = 'money' | 'bankrupt' | 'lose-turn';

export interface Wedge {
  kind: WedgeKind;
  value: number;      // dollar amount for 'money', 0 otherwise
  label: string;      // display text on the wheel
  color: string;      // hex fill color
}

// ─── Turn Phase ───────────────────────────────────────────────────────────────
// Strict state machine so no invalid button combos are possible.

export type TurnPhase =
  | 'waiting-to-spin'   // player must spin before anything
  | 'spinning'          // wheel animation in progress
  | 'choose-action'     // player spun a money wedge — pick consonant or vowel
  | 'guessing'          // player committed to guessing a letter
  | 'solving'           // player is typing a solve attempt
  | 'round-over'        // a player solved the puzzle
  | 'game-over';        // all 3 rounds complete

// ─── Player ───────────────────────────────────────────────────────────────────

export interface Player {
  id: 1 | 2;
  name: string;
  roundBalance: number;
  totalBalance: number;
}

// ─── Game State ───────────────────────────────────────────────────────────────

export interface GameState {
  players: [Player, Player];
  currentPlayerIndex: 0 | 1;   // index into players[]
  round: number;                // 1–3
  totalRounds: number;          // always 3
  puzzle: Puzzle;
  revealedLetters: Set<string>; // uppercase letters that have been revealed
  guessedLetters: Set<string>;  // all letters attempted (for display of "used")
  phase: TurnPhase;
  lastSpinResult: Wedge | null;
  roundHistory: RoundResult[];  // completed rounds
  message: string;              // contextual status message shown to players
}

export interface RoundResult {
  round: number;
  puzzle: Puzzle;
  winnerId: 1 | 2 | null; // null = nobody solved it (shouldn't happen in normal play)
}
