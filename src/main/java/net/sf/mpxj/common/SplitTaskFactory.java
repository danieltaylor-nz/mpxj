/*
 * file:       SplitTaskFactory
 * author:     Jon Iles
 * copyright:  (c) Packwood Software 2008
 * date:       25/11/2008
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj.common;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import net.sf.mpxj.LocalDateTimeRange;
import net.sf.mpxj.Task;
import net.sf.mpxj.TimephasedWork;

/**
 * This class contains methods to create lists of Dates representing
 * task splits.
 */
public final class SplitTaskFactory
{
   /**
    * Process the timephased resource assignment data to work out the
    * split structure of the task.
    *
    * @param task parent task
    * @param timephasedComplete completed resource assignment work
    * @param timephasedPlanned planned resource assignment work
    */
   public void processSplitData(Task task, List<TimephasedWork> timephasedComplete, List<TimephasedWork> timephasedPlanned)
   {
      LocalDateTime splitsComplete = null;
      TimephasedWork lastComplete = null;
      TimephasedWork firstPlanned = null;
      if (!timephasedComplete.isEmpty())
      {
         lastComplete = timephasedComplete.get(timephasedComplete.size() - 1);
         splitsComplete = lastComplete.getFinish();
      }

      if (!timephasedPlanned.isEmpty())
      {
         firstPlanned = timephasedPlanned.get(0);
      }

      List<LocalDateTimeRange> splits = new ArrayList<>();
      TimephasedWork lastAssignment = null;
      LocalDateTimeRange lastRange = null;
      for (TimephasedWork assignment : timephasedComplete)
      {
         if (lastAssignment != null && lastRange != null && lastAssignment.getTotalAmount().getDuration() != 0 && assignment.getTotalAmount().getDuration() != 0)
         {
            splits.remove(splits.size() - 1);
            lastRange = new LocalDateTimeRange(lastRange.getStart(), assignment.getFinish());
         }
         else
         {
            lastRange = new LocalDateTimeRange(assignment.getStart(), assignment.getFinish());
         }
         splits.add(lastRange);
         lastAssignment = assignment;
      }

      //
      // We may not have a split, we may just have a partially
      // complete split.
      //
      LocalDateTime splitStart = null;
      if (lastComplete != null && firstPlanned != null && lastComplete.getTotalAmount().getDuration() != 0 && firstPlanned.getTotalAmount().getDuration() != 0)
      {
         lastRange = splits.remove(splits.size() - 1);
         splitStart = lastRange.getStart();
      }

      lastAssignment = null;
      lastRange = null;
      for (TimephasedWork assignment : timephasedPlanned)
      {
         if (splitStart == null)
         {
            if (lastAssignment != null && lastRange != null && lastAssignment.getTotalAmount().getDuration() != 0 && assignment.getTotalAmount().getDuration() != 0)
            {
               splits.remove(splits.size() - 1);
               lastRange = new LocalDateTimeRange(lastRange.getStart(), assignment.getFinish());
            }
            else
            {
               lastRange = new LocalDateTimeRange(assignment.getStart(), assignment.getFinish());
            }
         }
         else
         {
            lastRange = new LocalDateTimeRange(splitStart, assignment.getFinish());
         }
         splits.add(lastRange);
         splitStart = null;
         lastAssignment = assignment;
      }

      //
      // We must have a minimum of 3 entries for this to be a valid split task
      //
      if (splits.size() > 2)
      {
         task.setSplits(splits);
         if (task.getActualFinish() == null)
         {
            // TODO: this is not correct for partially complete splits. It is possible that this value is stored rather than calculated?
            task.setCompleteThrough(splitsComplete);
         }
      }
      else
      {
         task.setSplits(null);
         task.setCompleteThrough(null);
      }
   }
}
