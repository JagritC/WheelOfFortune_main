/**
 * leaderboard.ts — localStorage-backed high score persistence.
 * No React, no side effects beyond localStorage reads/writes.
 */

const STORAGE_KEY = 'wof_leaderboard_v1';
const MAX_ENTRIES = 20;

export interface LeaderboardEntry {
  name: string;
  score: number;
  date: string; // ISO 8601
}

export function loadLeaderboard(): LeaderboardEntry[] {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return [];
    return JSON.parse(raw) as LeaderboardEntry[];
  } catch {
    return [];
  }
}

export function saveLeaderboard(entries: LeaderboardEntry[]): void {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(entries));
}

/**
 * Add one or more players' final scores.
 * Keeps the top MAX_ENTRIES, sorted by score descending.
 */
export function addEntries(
  newEntries: Array<{ name: string; score: number }>,
): LeaderboardEntry[] {
  const existing = loadLeaderboard();
  const date = new Date().toISOString();
  const merged = [
    ...existing,
    ...newEntries.map(e => ({ ...e, date })),
  ];
  const sorted = merged.sort((a, b) => b.score - a.score).slice(0, MAX_ENTRIES);
  saveLeaderboard(sorted);
  return sorted;
}

export function clearLeaderboard(): void {
  localStorage.removeItem(STORAGE_KEY);
}
