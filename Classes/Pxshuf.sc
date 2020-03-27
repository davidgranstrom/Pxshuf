/*
Copyright © 2018 David Granström

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

Pxshuf : ListPattern {
    embedInStream {arg inval;
        var item, firstItem;
        var index, lastList;
        var localList = list.copy;

        repeats.value(inval).do {arg j;
            localList = localList.scramble;
            firstItem = localList.first;

            lastList !? {
                if (firstItem == lastList.last) {
                    index = localList.rejectIndices {|a| a == firstItem }.first;
                    if (index.notNil) {
                        localList.swap(0, index);
                    } {
                        // exit here if we can't get a unique sequence
                        ^nil;
                    }
                }
            };

            lastList = localList.copy;

            localList.size.do {arg i;
                item = localList.wrapAt(i);
                inval = item.embedInStream(inval);
            };
        };
        ^inval;
    }
}
