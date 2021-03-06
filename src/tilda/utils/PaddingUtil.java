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

public class PaddingUtil
  {
    static final String[] _PADS = new String[100];
    
    static
      {
        StringBuilder Str = new StringBuilder();
        for (int i = 0; i < _PADS.length; ++i)
          {
            _PADS[i] = Str.toString();
            Str.append(" ");
          }
      }

    public static final String Pad(String S, int Padding)
      {
        return Padding >= 0 && Padding < _PADS.length ? S+_PADS[Padding-S.length()] : S;
      }

    public static final void Pad(StringBuilder Str, String S, int MaxLen)
      {
        String Pad = getPad(MaxLen - S.length());
        Str.append(S).append(Pad);
      }

    public static final String getPad(int Padding)
      {
        return  Padding <= 0 ? ""
              : Padding < _PADS.length ? _PADS[Padding]
              : _PADS[_PADS.length-1];
      }

  }
