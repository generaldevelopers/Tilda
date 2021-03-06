/* ===========================================================================
 * Copyright (C) 2015 CapsicoHealth Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tilda.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class DurationUtil
  {
    public static final long      MILLISECS_PER_SECOND     = 1000;
    public static final long      MILLISECS_PER_MINUTE     = 60 * MILLISECS_PER_SECOND;
    public static final long      MILLISECS_PER_HOUR       = 60 * MILLISECS_PER_MINUTE;
    public static final long      MILLISECS_PER_DAY        = 24 * MILLISECS_PER_HOUR;

    public static final long      NANOSECS_PER_MILLISECOND = 1000000;
    public static final long      NANOSECS_PER_SECOND      = MILLISECS_PER_SECOND * NANOSECS_PER_MILLISECOND;
    public static final long      NANOSECS_PER_MINUTE      = MILLISECS_PER_MINUTE * NANOSECS_PER_MILLISECOND;
    public static final long      NANOSECS_PER_HOUR        = MILLISECS_PER_HOUR * NANOSECS_PER_MILLISECOND;
    public static final long      NANOSECS_PER_DAY         = MILLISECS_PER_DAY * NANOSECS_PER_MILLISECOND;


    static protected NumberFormat F1                       = new DecimalFormat("###,###.00");

    public static String PrintDurationMilliSeconds(long NanoSeconds)
      {
        return F1.format(NanoSeconds / NANOSECS_PER_MILLISECOND) + "ms";
      }

    public static String PrintDurationSeconds(long NanoSeconds)
      {
        return F1.format(NanoSeconds / NANOSECS_PER_SECOND) + "s";
      }

    public static String PrintDurationMinutes(long NanoSeconds)
      {
        return F1.format(NanoSeconds / NANOSECS_PER_MINUTE) + "mn";
      }

    public static String PrintDurationHours(long NanoSeconds)
      {
        return F1.format(NanoSeconds / NANOSECS_PER_HOUR) + "h";
      }

    public static double getDurationSeconds(long NanoSeconds)
      {
        return 1.0 * NanoSeconds / NANOSECS_PER_SECOND;
      }

    public static String PrintDuration(long NanoSeconds)
      {
        long d = (long) Math.floor(NanoSeconds / (24 * 60 * 60 * 1000000000.0));
        NanoSeconds -= d * 24 * 60 * 60 * 1000000000;
        long h = (long) Math.floor(NanoSeconds / (60 * 60 * 1000000000.0));
        NanoSeconds -= h * 60 * 60 * 1000000000;
        long mn = (long) Math.floor(NanoSeconds / (60 * 1000000000.0));
        NanoSeconds -= mn * 60 * 1000000000;
        long s = (long) Math.floor(NanoSeconds / 1000000000.0);
        NanoSeconds -= s * 1000000000;
        long ms = NanoSeconds / 1000000;
        StringBuilder Str = new StringBuilder();
        if (d != 0)
          Str.append(d).append("d");
        if (h != 0 || Str.length() != 0)
          Str.append(Str.length() != 0 ? " " : "").append(h).append("h");
        if (mn != 0 || Str.length() != 0)
          Str.append(Str.length() != 0 ? " " : "").append(mn).append("mn");
        if (s != 0 || Str.length() != 0)
          Str.append(Str.length() != 0 ? " " : "").append(s).append("s");
        if (ms != 0)
          Str.append(Str.length() != 0 ? " " : "").append(ms).append("ms");
        return Str.toString();
      }

    /**
     * Given a length of elapsed time represented in ms, convert to a
     * user friendly translation into [days] [hours] minutes (hours and
     * days only printed if necessary)
     */
    public static String PrintDurationConciseFromMs(long MilliSeconds)
      {
        long d = (long) Math.floor(MilliSeconds / (24 * 60 * 60 * 1000.0));
        MilliSeconds -= d * 24 * 60 * 60 * 1000;
        long h = (long) Math.floor(MilliSeconds / (60 * 60 * 1000.0));
        MilliSeconds -= h * 60 * 60 * 1000;
        long mn = (long) Math.floor(MilliSeconds / (60 * 1000.0));

        StringBuilder Str = new StringBuilder();
        if (d != 0)
          Str.append(d).append("d");
        if (h != 0)
          Str.append(Str.length() != 0 ? " " : "").append(h).append("h");
        if (mn != 0 || Str.length() == 0)
          Str.append(Str.length() != 0 ? " " : "").append(mn).append("mn");

        return Str.toString();
      }
    
    
    public static String PrintPerformancePerSecond(long DurationNano, double Count)
      {
        return NumberFormatUtil.PrintWith2DecAnd000Sep(1000000000.0 * Count / DurationNano);
      }

    public static String PrintPerformancePerMinute(long DurationNano, double Count)
      {
        return NumberFormatUtil.PrintWith2DecAnd000Sep(60 * 1000000000.0 * Count / DurationNano);
      }

    public static String PrintPerformancePerHour(long DurationNano, double Count)
      {
        return NumberFormatUtil.PrintWith2DecAnd000Sep(60 * 60 * 1000000000.0 * Count / DurationNano);
      }

    public static String PrintPerformancePerDay(long DurationNano, double Count)
      {
        return NumberFormatUtil.PrintWith2DecAnd000Sep(24 * 60 * 60 * 1000000000.0 * Count / DurationNano);
      }

    public static String PrintExpectedRemainingTimeInMinutes(long DurationNano, int Count, int Total)
      {
        return NumberFormatUtil.PrintWith2DecAnd000Sep((Total - Count) / (60 * 1000000000.0 * Count / DurationNano));
      }
    
    
//    /**
//     * Start and end must not be null.
//     * 
//     * @param Start
//     * @param End
//     * @return
//     */
//    public static int NumberOfDays(Calendar Start, Calendar End)
//      {
//        if (Start == null || End == null)
//          return -1;
//        Start = (Calendar) Start.clone();
//        Start.set(Calendar.HOUR_OF_DAY, 0);
//        Start.set(Calendar.MINUTE, 0);
//        Start.set(Calendar.SECOND, 0);
//        Start.set(Calendar.MILLISECOND, 0);
//        End = (Calendar) End.clone();
//        End.set(Calendar.HOUR_OF_DAY, 0);
//        End.set(Calendar.MINUTE, 0);
//        End.set(Calendar.SECOND, 0);
//        End.set(Calendar.MILLISECOND, 0);
//
//        long startL = Start.getTimeInMillis() + Start.getTimeZone().getOffset(Start.getTimeInMillis());
//        long endL = End.getTimeInMillis() + End.getTimeZone().getOffset(End.getTimeInMillis());
//        return (int) ((endL - startL) / MILLISECS_PER_DAY) + 1;
//      }

//    public static int NumberOfHours(Calendar Start, Calendar End)
//      {
//        long startL = Start.getTimeInMillis() + Start.getTimeZone().getOffset(Start.getTimeInMillis());
//        long endL = End.getTimeInMillis() + End.getTimeZone().getOffset(End.getTimeInMillis());
//        return (int) ((endL - startL) / MILLISECS_PER_HOUR);
//      }

//    public static int NumberOfMinutes(Calendar Start, Calendar End)
//      {
//        long startL = Start.getTimeInMillis() + Start.getTimeZone().getOffset(Start.getTimeInMillis());
//        long endL = End.getTimeInMillis() + End.getTimeZone().getOffset(End.getTimeInMillis());
//        return (int) ((endL - startL) / MILLISECS_PER_MINUTE);
//      }
    
    public static double perSecond(long DurationNano, int Count)
      {
        return 1.0 * NANOSECS_PER_SECOND * Count / DurationNano;
      }

    public static double perMinute(long DurationNano, int Count)
      {
        return 1.0 * NANOSECS_PER_MINUTE * Count / DurationNano;
      }

    public static double perHour(long DurationNano, int Count)
      {
        return 1.0 * NANOSECS_PER_HOUR * Count / DurationNano;
      }

    public static long getDurationNano(Calendar Start, Calendar End)
      {
        return (End.getTimeInMillis() - Start.getTimeInMillis()) * NANOSECS_PER_MILLISECOND;
      }
  }
