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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
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

	/**
	 * This is the entry point method.
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

		aSendButton
				.addClickHandler(e -> sendTextToServer(aTextInput.getText()));

		aTextInput.addKeyUpHandler(e -> {
			if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER)
			{
				sendTextToServer(aTextInput.getText());
			}
		});
	}

	private void displayServerResponse(String sTitle, String sSentText,
			String sReceivedText)
	{
		DialogBox aDialogBox = new DialogBox();
		VerticalPanel aDialogPanel = new VerticalPanel();
		Label aTextToServer = new Label(sSentText);
		HTML aServerResponse = new HTML(sReceivedText);
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
				String sError = SERVER_ERROR + "<br>" + rCaught.toString();
				displayServerResponse("Server Error", sText, sError);
			}

			@Override
			public void onSuccess(String sResponse)
			{
				displayServerResponse("Server Response", sText, sResponse);
			}
		});
	}
}
