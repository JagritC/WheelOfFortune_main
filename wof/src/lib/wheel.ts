import type { Wedge } from './types';

// ─── Wheel Wedges ─────────────────────────────────────────────────────────────
// Matches the original game's 10 monetary outcomes, now with real label/color.
// Order here is the clockwise order on the rendered SVG wheel.

export const WEDGES: Wedge[] = [
  { kind: 'money',     value: 500,   label: '$500',        color: '#e74c3c' },
  { kind: 'money',     value: 900,   label: '$900',        color: '#8e44ad' },
  { kind: 'money',     value: 300,   label: '$300',        color: '#2980b9' },
  { kind: 'money',     value: 700,   label: '$700',        color: '#27ae60' },
  { kind: 'bankrupt',  value: 0,     label: 'BANKRUPT',    color: '#1a1a2e' },
  { kind: 'money',     value: 600,   label: '$600',        color: '#f39c12' },
  { kind: 'money',     value: 2500,  label: '$2500',       color: '#16a085' },
  { kind: 'money',     value: 400,   label: '$400',        color: '#c0392b' },
  { kind: 'lose-turn', value: 0,     label: 'LOSE A TURN', color: '#7f8c8d' },
  { kind: 'money',     value: 800,   label: '$800',        color: '#d35400' },
  { kind: 'money',     value: 1200,  label: '$1200',       color: '#6c3483' },
  { kind: 'money',     value: 200,   label: '$200',        color: '#1a5276' },
  { kind: 'money',     value: 10000, label: '$10000',      color: '#f1c40f' },
  { kind: 'money',     value: 450,   label: '$450',        color: '#117a65' },
  { kind: 'money',     value: 350,   label: '$350',        color: '#922b21' },
  { kind: 'bankrupt',  value: 0,     label: 'BANKRUPT',    color: '#1a1a2e' },
];

export const WEDGE_COUNT = WEDGES.length;
export const WEDGE_ANGLE = 360 / WEDGE_COUNT; // degrees per wedge

/**
 * Returns the index (0-based) of a randomly chosen wedge.
 * No weighting — every wedge has equal probability, matching original behavior.
 */
export function spinWheel(): number {
  return Math.floor(Math.random() * WEDGE_COUNT);
}

/**
 * Given a landed wedge index, returns that Wedge object.
 */
export function getWedge(index: number): Wedge {
  return WEDGES[index];
}
