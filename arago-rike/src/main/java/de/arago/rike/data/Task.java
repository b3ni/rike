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
package de.arago.rike.data;

import java.io.Serializable;
import java.util.Date;


public class Task implements Serializable {

	private long id;
	private String title;
	private String url;
	private String owner;
	private Date start;
	private Date end;
	private String creator;
	private Date created;
	private Integer sizeEstimated;
	private Integer size;
	private Integer hoursSpent;
	private Milestone milestone;
	private Artifact artifact;
	private String priority;
	private String challenge;
	private String status;

	public static enum Status {

		UNKNOWN, OPEN, IN_PROGRESS, DONE
	}

	public static enum Challenge {

		DIFFICULT, AVERAGE, EASY
	}

	public static enum Priority {

		HIGH, NORMAL, LOW
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public String getStatus() {
		return status;
	}

	public Status getStatusEnum() {
		return Status.valueOf(status.toUpperCase());
	}

	public void setStatus(String what) {
		setStatus(Status.valueOf(what.toUpperCase()));
	}

	public void setStatus(Status what) {
		status = what.toString().toLowerCase();
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStart() {
		return start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getEnd() {
		return end;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public Integer getSizeEstimated() {
		return sizeEstimated;
	}

	public void setSizeEstimated(Integer sizeEstimated) {
		this.sizeEstimated = sizeEstimated;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getHoursSpent() {
		return hoursSpent;
	}

	public void setHoursSpent(Integer spent) {
		hoursSpent = spent;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		setPriority(Priority.valueOf(priority.toUpperCase()));
	}

	public String getChallenge() {
		return challenge;
	}

	public Challenge getChallengeEnum() {
		return Challenge.valueOf(challenge.toUpperCase());
	}

	public void setChallenge(String challenge) {
		setChallenge(Challenge.valueOf(challenge.toUpperCase()));
	}

	public void setChallenge(Challenge s) {
		challenge = s.toString().toLowerCase();
	}

	public void setPriority(Priority p) {
		priority = p.toString().toLowerCase();
	}

	public Priority getPriorityEnum() {
		return Priority.valueOf(priority.toUpperCase());
	}

	public void setArtifact(Artifact what) {
		artifact = what;
	}

	public Artifact getArtifact() {
		return artifact;
	}
}
