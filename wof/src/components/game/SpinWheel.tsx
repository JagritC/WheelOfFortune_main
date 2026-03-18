import { useEffect, useRef, useState } from 'react';
import { WEDGES, WEDGE_ANGLE } from '../../lib/wheel';
import type { Wedge } from '../../lib/types';
import './SpinWheel.css';

interface Props {
  spinning: boolean;
  targetIndex: number;
  onSpinComplete: (wedge: Wedge) => void;
}

const WHEEL_SIZE = 340;
const CX = WHEEL_SIZE / 2;
const CY = WHEEL_SIZE / 2;
const R = WHEEL_SIZE / 2 - 8; // outer radius

/** Convert polar coords to SVG cartesian */
function polar(cx: number, cy: number, r: number, angleDeg: number) {
  const rad = ((angleDeg - 90) * Math.PI) / 180;
  return { x: cx + r * Math.cos(rad), y: cy + r * Math.sin(rad) };
}

/** SVG arc path for a single wedge */
function wedgePath(index: number): string {
  const startAngle = index * WEDGE_ANGLE;
  const endAngle = startAngle + WEDGE_ANGLE;
  const start = polar(CX, CY, R, startAngle);
  const end = polar(CX, CY, R, endAngle);
  const largeArc = WEDGE_ANGLE > 180 ? 1 : 0;
  return `M ${CX} ${CY} L ${start.x} ${start.y} A ${R} ${R} 0 ${largeArc} 1 ${end.x} ${end.y} Z`;
}

/** Label position for a wedge */
function labelPos(index: number) {
  return polar(CX, CY, R * 0.72, index * WEDGE_ANGLE + WEDGE_ANGLE / 2);
}

/** Label rotation so text follows the wedge direction */
function labelRotation(index: number): number {
  return index * WEDGE_ANGLE + WEDGE_ANGLE / 2;
}

const SPIN_DURATION_MS = 3000;
// Minimum full rotations before stopping
const MIN_ROTATIONS = 5;

export function SpinWheel({ spinning, targetIndex, onSpinComplete }: Props) {
  const [rotation, setRotation] = useState(0);
  const animRef = useRef<number | null>(null);
  const startTimeRef = useRef<number | null>(null);
  const startRotationRef = useRef(0);
  const targetRotationRef = useRef(0);
  const hasReportedRef = useRef(false);

  useEffect(() => {
    if (!spinning) return;

    hasReportedRef.current = false;
    startRotationRef.current = rotation;

    // The wheel pointer is at the top (0°). We want wedge `targetIndex` to end
    // up under the pointer. Wedge 0 starts at 0°, so wedge i starts at i * WEDGE_ANGLE.
    // We need to rotate so that the middle of wedge i is at 0° (top).
    const wedgeMidAngle = targetIndex * WEDGE_ANGLE + WEDGE_ANGLE / 2;
    // How much extra we need beyond full rotations
    const normalizedCurrent = ((rotation % 360) + 360) % 360;
    const needed = ((360 - wedgeMidAngle - normalizedCurrent) + 360) % 360;
    targetRotationRef.current =
      rotation + MIN_ROTATIONS * 360 + needed;

    startTimeRef.current = null;

    function animate(ts: number) {
      if (!startTimeRef.current) startTimeRef.current = ts;
      const elapsed = ts - startTimeRef.current;
      const progress = Math.min(elapsed / SPIN_DURATION_MS, 1);
      // Ease-out cubic
      const eased = 1 - Math.pow(1 - progress, 3);

      const current =
        startRotationRef.current +
        (targetRotationRef.current - startRotationRef.current) * eased;
      setRotation(current);

      if (progress < 1) {
        animRef.current = requestAnimationFrame(animate);
      } else {
        if (!hasReportedRef.current) {
          hasReportedRef.current = true;
          onSpinComplete(WEDGES[targetIndex]);
        }
      }
    }

    animRef.current = requestAnimationFrame(animate);
    return () => {
      if (animRef.current !== null) cancelAnimationFrame(animRef.current);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [spinning, targetIndex]);

  return (
    <div className="spin-wheel-wrapper">
      <div className="wheel-container">
        {/* Pointer / arrow at top */}
        <div className="wheel-pointer">▼</div>

        <svg
          width={WHEEL_SIZE}
          height={WHEEL_SIZE}
          viewBox={`0 0 ${WHEEL_SIZE} ${WHEEL_SIZE}`}
          className="wheel-svg"
          style={{ transform: `rotate(${rotation}deg)` }}
        >
          {/* Wedges */}
          {WEDGES.map((wedge, i) => (
            <path
              key={i}
              d={wedgePath(i)}
              fill={wedge.color}
              stroke="#0a0a1a"
              strokeWidth={2}
            />
          ))}

          {/* Labels */}
          {WEDGES.map((wedge, i) => {
            const pos = labelPos(i);
            const rot = labelRotation(i);
            return (
              <text
                key={i}
                x={pos.x}
                y={pos.y}
                textAnchor="middle"
                dominantBaseline="middle"
                fill={wedge.kind === 'bankrupt' ? '#fff' : '#fff'}
                fontSize={wedge.kind !== 'money' ? '9' : wedge.value >= 10000 ? '9' : '10'}
                fontWeight="800"
                fontFamily="Arial, sans-serif"
                transform={`rotate(${rot}, ${pos.x}, ${pos.y})`}
              >
                {wedge.label}
              </text>
            );
          })}

          {/* Center cap */}
          <circle cx={CX} cy={CY} r={18} fill="#1a1a2e" stroke="#4a90d9" strokeWidth={2} />
          <circle cx={CX} cy={CY} r={6} fill="#4a90d9" />
        </svg>
      </div>
    </div>
  );
}
