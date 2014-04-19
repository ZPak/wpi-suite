/*******************************************************************************
 * Copyright (c) 2013-2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder, struct-by-lightning
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.controller;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.PlanningPokerGame;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.User;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds by adding a new planning poker game.
 * 
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class AddUserController {

	private static AddUserController instance;
	private AddUserRequestObserver observer;

	/**
	 * Construct an AddUserController for the given model, view
	 * pair
	 */
	private AddUserController() {
		observer = new AddUserRequestObserver(this);
	}

	/**
	 * 
	 * @return the instance of the AddUserController or creates one
	 *         if it does not exist.
	 */
	public static AddUserController getInstance() {
		if (instance == null) {
			instance = new AddUserController();
		}

		return instance;
	}

	/**
	 * This method adds a PlanningPokerGame to the server.
	 * 
	 * @param newPlanningPokerGame
	 *            is the PlanningPokerGame to be added to the server.
	 */
	public void AddUser(User newUser) {
		final Request request = Network.getInstance().makeRequest(
				"planningpoker/planningpokergame", HttpMethod.PUT); // PUT ==
																	// create
		request.setBody(newUser.toJSON()); // put the new
														// PlanningPokerGame in
														// the body of the
														// request
		request.addObserver(observer); // add an observer to process the
										// response
		request.send();
	}
}
