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

package tilda.utils.comparators;

import java.util.Comparator;

public class ReverseCaseInsensitiveStringComparator implements Comparator<String>
{
  @Override
  public int compare(String arg1, String arg2)
    {
      if (arg1 == null)
        return 1;
      if (arg2 == null)
        return -1;
      return -arg1.compareToIgnoreCase(arg2);
    }

}