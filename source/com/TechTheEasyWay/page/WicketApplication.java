package  com.TechTheEasyWay.page;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication{
	
	public WicketApplication()
	{
		super();
	}
	
	@Override
	public Class<? extends Page> getHomePage()
	{
		return SignInPage.class;
	}

	@Override
	public void init()
	{
		super.init();
	}
}
