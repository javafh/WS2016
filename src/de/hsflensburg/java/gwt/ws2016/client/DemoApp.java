package de.hsflensburg.java.gwt.ws2016.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hsflensburg.java.gwt.ws2016.shared.DemoService;
import de.hsflensburg.java.gwt.ws2016.shared.DemoServiceAsync;

/**********************************
 * This class defines the main entry point of this application.
 */
public class DemoApp implements EntryPoint
{
	/**
	 * A message to be displayed to the user when the server cannot be reached
	 * or returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create the remote service for communication with the server.
	 */
	private final DemoServiceAsync aDemoService = GWT.create(DemoService.class);

	private Button aSendButton;

	/**********************************
	 * The starting method of the GWT {@link EntryPoint}, comparable to a main
	 * method in other application types.
	 */
	@Override
	public void onModuleLoad()
	{
		final TextArea aTextInput = new TextArea();

		aSendButton = new Button("Send");

		aSendButton.addStyleName("sendButton");

		RootPanel.get("nameFieldContainer").add(aTextInput);
		RootPanel.get("sendButtonContainer").add(aSendButton);

		aTextInput.setFocus(true);
		aTextInput.addKeyUpHandler(e -> {
			if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER)
			{
				sendTextToServer(aTextInput.getText());
			}
		});

		aSendButton
				.addClickHandler(e -> sendTextToServer(aTextInput.getText()));

	}

	/**********************************
	 * Displays a server error.
	 *
	 * @param sSentText The text that had been sent to the server
	 * @param rCaught The exception received from the server
	 */
	private void displayServerError(String sSentText, Throwable rCaught)
	{
		String sError = SERVER_ERROR + "<br>" + rCaught.toString();
		displayServerResponse("Server Error", sSentText, sError);
	}

	/**********************************
	 * Displays a dialog box with a response that has been received from the
	 * server.
	 *
	 * @param sTitle The dialog title
	 * @param sSentText The text that had been sent to the server
	 * @param sResponse The response text from the server
	 */
	private void displayServerResponse(String sTitle, String sSentText,
			String sResponse)
	{
		DialogBox aDialogBox = new DialogBox();
		VerticalPanel aDialogPanel = new VerticalPanel();
		Label aTextToServer = new Label(sSentText);
		HTML aServerResponse = new HTML(sResponse);
		Button aCloseButton = new Button("Close");

		aDialogBox.setText(sTitle);
		aDialogBox.setAnimationEnabled(true);

		aCloseButton.getElement().setId("closeButton");
		aDialogPanel.addStyleName("dialogPanel");

		aDialogPanel.add(new HTML("<b>Text sent to server:</b>"));
		aDialogPanel.add(aTextToServer);
		aDialogPanel.add(new HTML("<br><b>Server replied with:</b>"));
		aDialogPanel.add(aServerResponse);
		aDialogPanel.add(aCloseButton);

		aDialogBox.setWidget(aDialogPanel);
		aDialogBox.center();
		aCloseButton.setFocus(true);

		aCloseButton.addClickHandler(e -> {
			aDialogBox.hide();
			aSendButton.setEnabled(true);
		});
	}

	/**
	 * Send the name from the nameField to the server and wait for a response.
	 */
	private void sendTextToServer(String sText)
	{
		aSendButton.setEnabled(false);

		aDemoService.sendText(sText, new AsyncCallback<String>()
		{
			@Override
			public void onFailure(Throwable rCaught)
			{
				displayServerError(sText, rCaught);
			}

			@Override
			public void onSuccess(String sResponse)
			{
				displayServerResponse("Server Response", sText, sResponse);
			}
		});
	}
}
