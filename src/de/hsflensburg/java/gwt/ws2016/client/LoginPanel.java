package de.hsflensburg.java.gwt.ws2016.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPanel extends Composite
{
	/**********************************
	 * Creates a new instance of this class.
	 *
	 * @see Composite#Composite()
	 */
	public LoginPanel(LoginHandler rLoginHandler)
	{
		Grid aMainPanel = new Grid(3, 2);
		TextBox aLoginNameField = new TextBox();
		TextBox aPasswordField = new TextBox();
		Button aLoginButton = new Button("Login");

		aMainPanel.setWidget(0, 0, new Label("Benutzername:"));
		aMainPanel.setWidget(0, 1, aLoginNameField);
		aMainPanel.setWidget(1, 0, new Label("Passwort:"));
		aMainPanel.setWidget(1, 1, aPasswordField);
		aMainPanel.setWidget(2, 1, aLoginButton);

		aMainPanel.addStyleName(getClass().getSimpleName());

		aLoginButton.addClickHandler(e -> rLoginHandler.handleLogin(
				aLoginNameField.getText(), aPasswordField.getText()));

		initWidget(aMainPanel);
	}

	public static interface LoginHandler
	{
		public void handleLogin(String sUserName, String sPassword);
	}
}
