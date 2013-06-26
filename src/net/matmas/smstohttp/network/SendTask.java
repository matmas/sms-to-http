package net.matmas.smstohttp.network;

import java.io.PrintWriter;

public class SendTask extends NetworkTask {
	
	private String parameters;
	
	public SendTask(String URL, String parameters, SuccessCallback onSuccessCallback, FailureCallback onFailureCallback) {
		this.URL = URL;
		this.parameters = parameters;
		this.onSuccessCallback = onSuccessCallback;
		this.onFailureCallback = onFailureCallback;
	}
	
	@Override
	protected void send(PrintWriter out) {
		out.print(parameters);
	}
}
