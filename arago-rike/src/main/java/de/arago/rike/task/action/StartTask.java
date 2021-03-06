/**
 * Copyright (c) 2010 arago AG, http://www.arago.de/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.arago.rike.task.action;

import de.arago.portlet.Action;
import de.arago.portlet.util.SecurityHelper;

import de.arago.data.IDataWrapper;
import de.arago.rike.util.TaskHelper;
import de.arago.rike.data.Task;
import de.arago.rike.task.StatisticHelper;

import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang.StringEscapeUtils;

public class StartTask implements Action {

  @Override
	public void execute(IDataWrapper data) throws Exception 
  {
   	if (data.getRequestAttribute("id") != null) {
			String user = SecurityHelper.getUserEmail(data.getUser());

			if (TaskHelper.getTasksInProgressForUser(user).size() < 3) {
				Task task = TaskHelper.getTask(data.getRequestAttribute("id"));

				if (!TaskHelper.canDoTask(user, task) || task.getStatusEnum() != Task.Status.OPEN) {
					return;
				}

				task.setOwner(user);
				task.setStart(new Date());
				task.setStatus(Task.Status.IN_PROGRESS);

				TaskHelper.save(task);
				StatisticHelper.update();

				if(task.getArtifact().getId().longValue()!=TaskHelper.OTHER_ARTEFACT_ID){
					long price = task.getSizeEstimated();
					
					if(task.getChallengeEnum()==Task.Challenge.DIFFICULT)
						price *= 2;
					else if(task.getChallengeEnum()==Task.Challenge.EASY)
						price /= 2;
					
					if(task.getPriorityEnum()==Task.Priority.HIGH)
						price*=2;
					else if(task.getPriorityEnum()==Task.Priority.LOW)
						price /=2;
					
          System.err.println("{task} " + user + " started task #" + task.getId() + " (calculated price is "+price+")");
          
					TaskHelper.changeAccount(user, price);
					TaskHelper.changeAccount(task.getCreator(), -price);
				}
        
				data.setSessionAttribute("task", task);

				HashMap<String, Object> notificationParam = new HashMap<String, Object>();

				notificationParam.put("id", task.getId().toString());
				data.setEvent("TaskUpdateNotification", notificationParam);

				TaskHelper.log(" started Task #" + task.getId().toString() + " <a href=\"[selectTask:" + task.getId().toString() + "]\">" + StringEscapeUtils.escapeHtml(task.getTitle()) + "</a> ", task, user, data);
			}
		}
	}
}
