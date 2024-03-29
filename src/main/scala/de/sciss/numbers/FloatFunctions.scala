/*
 *  FloatFunctions.scala
 *  (Numbers)
 *
 *  Copyright (c) 2013-2018 Hanns Holger Rutz. All rights reserved.
 *
 *	This software is published under the GNU Lesser General Public License v2.1+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.numbers

/** Common functions for `Float` values. */
object FloatFunctions {
  // -------- unary ops --------

  @inline def abs       (f: Float): Float = math.abs(f)
  @inline def ceil      (f: Float): Float = math.ceil(f).toFloat
  @inline def floor     (f: Float): Float = math.floor(f).toFloat
  @inline def frac      (f: Float): Float = (f - math.floor(f)).toFloat

  // according to jmc
  @inline def signum    (f: Float): Float = math.signum(f)
  @inline def squared   (f: Float): Float = f * f
  @inline def sqrt      (f: Float): Float = math.sqrt(f).toFloat
  @inline def exp       (f: Float): Float = math.exp(f).toFloat
  @inline def midiCps   (f: Float): Float = (440 * math.pow(2, (f - 69) / 12)).toFloat
  @inline def cpsMidi   (f: Float): Float = (math.log(math.abs(f) / 440) / Log2 * 12 + 69).toFloat
  @inline def midiRatio (f: Float): Float = math.pow(2, f / 12).toFloat
  @inline def ratioMidi (f: Float): Float = (12 * math.log(math.abs(f)) / Log2).toFloat
  @inline def dbAmp     (f: Float): Float = math.pow(10, f * 0.05).toFloat
  @inline def ampDb     (f: Float): Float = (math.log10(f) * 20).toFloat
  @inline def octCps    (f: Float): Float = (440 * math.pow(2, f - 4.75)).toFloat
  @inline def cpsOct    (f: Float): Float = (math.log(math.abs(f) / 440) / Log2 + 4.75).toFloat
  @inline def log       (f: Float): Float = math.log(f).toFloat
  @inline def log2      (f: Float): Float = (math.log(f) / Log2).toFloat
  @inline def log10     (f: Float): Float = math.log10(f).toFloat
  @inline def sin       (f: Float): Float = math.sin(f).toFloat
  @inline def cos       (f: Float): Float = math.cos(f).toFloat
  @inline def tan       (f: Float): Float = math.tan(f).toFloat
  @inline def asin      (f: Float): Float = math.asin(f).toFloat
  @inline def acos      (f: Float): Float = math.acos(f).toFloat
  @inline def atan      (f: Float): Float = math.atan(f).toFloat
  @inline def sinh      (f: Float): Float = math.sinh(f).toFloat
  @inline def cosh      (f: Float): Float = math.cosh(f).toFloat
  @inline def tanh      (f: Float): Float = math.tanh(f).toFloat

  // -------- binary ops --------

  @inline def +       (a: Float, b: Float): Float   = a + b
  @inline def -       (a: Float, b: Float): Float   = a - b
  @inline def *       (a: Float, b: Float): Float   = a * b
  @inline def div     (a: Float, b: Float): Int     = if (b == 0f) 0 else math.floor(a / b).toInt
  @inline def /       (a: Float, b: Float): Float   = a / b
  @inline def %       (a: Float, b: Float): Float   = a % b

  // @inline def sig_==( a: Float, b: Float ) : Int  = if( a == b ) 1 else 0
  // @inline def sig_!=( a: Float, b: Float ) : Int  = if( a != b ) 1 else 0
  @inline def <       (a: Float, b: Float): Boolean = a < b
  @inline def >       (a: Float, b: Float): Boolean = a > b
  @inline def <=      (a: Float, b: Float): Boolean = a <= b
  @inline def >=      (a: Float, b: Float): Boolean = a >= b
  @inline def min     (a: Float, b: Float): Float   = math.min(a, b)
  @inline def max     (a: Float, b: Float): Float   = math.max(a, b)
  @inline def &       (a: Float, b: Float): Float   = a.toInt & b.toInt
  @inline def |       (a: Float, b: Float): Float   = a.toInt | b.toInt
  @inline def ^       (a: Float, b: Float): Float   = a.toInt ^ b.toInt

  // lcm
  // gcd

  @inline def roundTo   (a: Float, b: Float): Float   = if (b == 0) a else (math.floor(a / b + 0.5f) * b).toFloat
  @inline def roundUpTo (a: Float, b: Float): Float   = if (b == 0) a else (math.ceil (a / b) * b).toFloat
  @inline def trunc     (a: Float, b: Float): Float   = if (b == 0) a else (math.floor(a / b) * b).toFloat
  @inline def atan2     (a: Float, b: Float): Float   = math.atan2(a, b).toFloat
  @inline def hypot     (a: Float, b: Float): Float   = math.hypot(a, b).toFloat

  @inline def hypotApx  (a: Float, b: Float): Float = {
    val aa  = math.abs(a)
    val ba  = math.abs(b)
    val min = math.min(aa, ba)
    aa + ba - (Sqrt2.toFloat - 1) * min
  }

  @inline def pow     (a: Float, b: Float): Float = math.pow(a, b).toFloat

  // <<
  // >>
  // UnsgnRghtShft
  // fill

  @inline def difSqr  (a: Float, b: Float): Float = a * a - b * b
  @inline def sumSqr  (a: Float, b: Float): Float = a * a + b * b

  @inline def sqrSum  (a: Float, b: Float): Float = {
    val z = a + b
    z * z
  }

  @inline def sqrDif  (a: Float, b: Float): Float = {
    val z = a - b
    z * z
  }

  @inline def absDif  (a: Float, b: Float): Float = math.abs(a - b)
  @inline def clip2   (a: Float, b: Float): Float = clip(a, -b, b)
  @inline def excess  (a: Float, b: Float): Float = a - math.max(math.min(a, b), -b)
  @inline def fold2   (a: Float, b: Float): Float = fold(a, -b, b)
  @inline def wrap2   (a: Float, b: Float): Float = wrap(a, -b, b)

  // handles negative numbers differently than a % b
  @inline def mod(a: Float, b: Float): Float =
    if (b == 0f) 0f
    else if (math.signum(a) == math.signum(b)) {
      a % b
    } else {
      val c = -a % b
      if (c == 0f) 0f else b - c
    }

  // -------- n-ary ops --------

  @inline def clip(in: Float, low: Float, high: Float): Float = math.max(math.min(in, high), low)

  @inline def fold(in: Float, low: Float, high: Float): Float = {
    val x = in - low
    // avoid the divide if possible
    if (in >= high) {
      val f = high + high - in
      if (f >= low) return f
    } else if (in < low) {
      val f = low + low - in
      if (f < high) return f
    } else return in

    if (high == low) return low
    // ok do the divide
    val range   = high - low
    val range2  = range + range
    val c       = x - range2 * math.floor(x / range2).toFloat
    low + (if (c >= range) range2 - c else c)
  }

  @inline def wrap(in: Float, low: Float, high: Float): Float =
    if (in >= high) { // avoid the divide if possible
      val range = high - low
      val in2 = in - range
      if (in2 < high) in2
      else if (high == low) low
      else {
        in2 - range * math.floor((in2 - low) / range).toFloat
      }
    } else if (in < low) {
      val range = high - low
      val in2 = in + range
      if (in2 >= low) in2
      else if (high == low) low
      else {
        in2 - range * math.floor((in2 - low) / range).toFloat
      }
    } else in

  @inline def linLin(in: Float, inLow: Float, inHigh: Float, outLow: Float, outHigh: Float): Float =
    (in - inLow) / (inHigh - inLow) * (outHigh - outLow) + outLow

  @inline def linExp(in: Float, inLow: Float, inHigh: Float, outLow: Float, outHigh: Float): Float =
    (math.pow(outHigh / outLow, (in - inLow) / (inHigh - inLow)) * outLow).toFloat

  @inline def expLin(in: Float, inLow: Float, inHigh: Float, outLow: Float, outHigh: Float): Float =
    (math.log(in / inLow) / math.log(inHigh / inLow) * (outHigh - outLow) + outLow).toFloat

  @inline def expExp(in: Float, inLow: Float, inHigh: Float, outLow: Float, outHigh: Float): Float =
    (math.pow(outHigh / outLow, math.log(in / inLow) / math.log(inHigh / inLow)) * outLow).toFloat
}