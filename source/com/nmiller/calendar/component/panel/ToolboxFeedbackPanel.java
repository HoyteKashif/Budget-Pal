package com.nmiller.calendar.component.panel;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 * Custom feedback panel utilizing css
 */
public class ToolboxFeedbackPanel extends FeedbackPanel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 4158966554899798011L;
  
  /** default wicket id */
  public static final String DEFAULT_ID = "pnlFeedback";
  
  /**
   * Used to determine the feedback level to show and what style to use.
   */
  private enum FeedbackLevel
  {
    /** Fatal Error Message Level. */
    FATAL(FeedbackMessage.FATAL, "A Fatal Error has Occurred", "container-fatal-error"),
    /** Error Message Level. */
    ERROR(FeedbackMessage.ERROR, "Error Message(s)", "container-error"),
    /** Warning Message Level. */
    WARNING(FeedbackMessage.WARNING, "Warning Message(s)", "container-warning"),
    /** Success Message Level. */
    SUCCESS(FeedbackMessage.SUCCESS, "Success", "container-success"),
    /** Information Message Level. */
    INFO(FeedbackMessage.INFO, "Information", "container-info"),
    /** Debug Message Level. */
    DEBUG(FeedbackMessage.DEBUG, "Debug Information", "container-debug");
    
    private int m_iWicketLevel;
    private String m_strTitle;
    private String m_strCSSClass;
    
    private FeedbackLevel(final int p_iWicketLevel, final String p_strTitle, final String p_strCSSClass)
    {
      m_iWicketLevel = p_iWicketLevel;
      m_strTitle = p_strTitle;
      m_strCSSClass = p_strCSSClass;
    }
    
    /**
     * Returns the CSS class to use for this feedback level.
     * @return String
     */
    public String getCSSClass()
    {
      return m_strCSSClass;
    }
    
    /**
     * Returns the title to display on the box.
     * @return String
     */
    public String getTitle()
    {
      return m_strTitle;
    }
    
    /**
     * Returns the Wicket level for this FeedbackLevel.
     * @return int
     */
    public int getWicketLevel()
    {
      return m_iWicketLevel;
    }
    
    /**
     * Looks for the highest level error message to display.
     * 
     * @param p_oFeedbackPanel The feedback panel that this is for.
     * @return ErrorLevel
     */
    public static FeedbackLevel getFeedbackLevel(final FeedbackPanel p_oFeedbackPanel)
    {
      // uses the order in which they are declared above!
      for(final FeedbackLevel oLevel : values())
      {
        if(p_oFeedbackPanel.anyMessage(oLevel.getWicketLevel()))
        {
          return oLevel;
        }
      }
      
      // it's unknown so go with error.
      return ERROR;
    }
  }
  
  /**
   * Create a new feed back panel.
   * @param p_id wicket id
   */
  public ToolboxFeedbackPanel(final String p_strId)
  {
    super(p_strId);
    
    add(new Label("strHeading", new Model<String>()
    {
      /** serialVersionUID. */
      private static final long serialVersionUID = 561456231215724123L;
      
      @Override
      public String getObject()
      {
        return FeedbackLevel.getFeedbackLevel(ToolboxFeedbackPanel.this).getTitle();
      }
    }));
    
    // Change the color and image using CSS classes.
    add(AttributeModifier.replace("class", new Model<String>()
    {
      /** serialVersionUID. */
      private static final long serialVersionUID = 561456231215724123L;
      
      @Override
      public String getObject()
      {
        return FeedbackLevel.getFeedbackLevel(ToolboxFeedbackPanel.this).getCSSClass();
      }
    }));
  }
  
  /**
   * We override onConfigure and setVisible in order
   * for the panel to work on stateless pages.
   */
  @Override
  protected void onConfigure()
  {
    super.onConfigure();
    setVisible(anyMessage());
  }
}
