package com.nmiller.calendar.component.label;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import com.nmiller.calendar.util.helper.StaticValuesHelper;
import com.nmiller.calendar.util.helper.ValidationHelper;


public class BaseLabel extends Label
{
  /** serialVersionUID */
  private static final long serialVersionUID = 3557155007672641448L;
  
  /**
   * Different label types.
   */
  public enum LabelType
  {
    TEXT_REPLACEMENT, AUTO_HIDE
  }
  
  /** the type of label */
  private LabelType m_eLabelType;
  
  /** model holding the replacement text */
  private IModel<String> m_oReplacementTextModel;
  
  /** is multi line label? */
  private boolean m_bMultiLineLabelFlag;
  
  /**
   * Create a BaseLabel with no label type specified.
   * 
   * @param p_strId           label wicket id
   */
  protected BaseLabel(final String p_strId)
  {
    this(p_strId, null, null);
  }
  
  /**
   * Create a BaseLabel of the given label type.
   * 
   * @param p_strId           label wicket id
   * @param p_eLabelType      LabelType
   */
  protected BaseLabel(final String p_strId, final LabelType p_eLabelType)
  {
    this(p_strId, p_eLabelType, null);
  }
  
  /**
   * Create a BaseLabel of the given label type and label model.
   * 
   * @param p_strId           label wicket id
   * @param p_eLabelType      LabelType
   * @param p_oLabelModel     model backing the label
   */
  protected BaseLabel(final String p_strId, final LabelType p_eLabelType, final IModel<?> p_oLabelModel)
  {
    super(p_strId, p_oLabelModel);
    m_eLabelType = p_eLabelType;
  }
  
  /**
   * Set the replacement text when using LabelType.TEXT_REPLACEMENT
   * 
   * @param p_strReplacementText      replacement text
   * @return BaseLabel
   */
  public BaseLabel setReplacementText(final String p_strReplacementText)
  {
    return setReplacementText(Model.of(p_strReplacementText));
  }
  
  /**
   * Set the replacement text when using LabelType.TEXT_REPLACEMENT
   * 
   * @param p_oReplacementTextModel
   * @return BaseLabel
   */
  public BaseLabel setReplacementText(final IModel<String> p_oReplacementTextModel)
  {
    m_oReplacementTextModel = p_oReplacementTextModel;
    return this;
  }
  
  /**
   * Set whether the label is a multi line label or not.
   * Multi line labels replace newline characters with html line breaks.
   * 
   * @param p_bMultiLineLabelFlag
   * @return BaseLabel
   */
  public BaseLabel setMultiLineLabelFlag(final boolean p_bMultiLineLabelFlag)
  {
    m_bMultiLineLabelFlag = p_bMultiLineLabelFlag;
    return this;
  }
  
  @Override
  public void onComponentTagBody(final MarkupStream p_oMarkupStream, final ComponentTag p_oOpenTag)
  {
    final String strModelObject = getDefaultModelObjectAsString();
    if(LabelType.TEXT_REPLACEMENT.equals(m_eLabelType) && !ValidationHelper.hasText(strModelObject))
    {
      if(null != m_oReplacementTextModel && ValidationHelper.hasText(m_oReplacementTextModel.getObject()))
      {
        replaceComponentTagBody(p_oMarkupStream, p_oOpenTag, m_oReplacementTextModel.getObject());
      }
      else
      {
        replaceComponentTagBody(p_oMarkupStream, p_oOpenTag, StaticValuesHelper.NONE);
      }
    }
    else
    {
      replaceComponentTagBody(p_oMarkupStream, p_oOpenTag, getComponentTagBody(strModelObject));
    }
  }
  
  /**
   * Get the component tag body from the string model object.
   * 
   * @param p_strModelObject      model object as string
   * @return CharSequence
   */
  private CharSequence getComponentTagBody(final String p_strModelObject)
  {
    return m_bMultiLineLabelFlag ? Strings.toMultilineMarkup(p_strModelObject) : p_strModelObject;
  }
  
  @Override
  public void onConfigure()
  {
    if(LabelType.AUTO_HIDE.equals(m_eLabelType))
    {
      setVisible(ValidationHelper.hasText(getDefaultModelObjectAsString()));
    }
  }
}
