package com.nmiller.calendar.component.label;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * ReplacementLabel is a class that displays replacement text
 * if the model backing the label is null or empty.
 */
public class ReplacementLabel extends BaseLabel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 7228895390829976147L;
  
  public ReplacementLabel(final String p_strId)
  {
    this(p_strId, null, null);
  }
  
  public ReplacementLabel(final String p_strId, final IModel<?> p_oLabelModel)
  {
    this(p_strId, p_oLabelModel, null);
  }
  
  public ReplacementLabel(final String p_strId, final String p_strReplacementText)
  {
    this(p_strId, null, Model.of(p_strReplacementText));
  }
  
  public ReplacementLabel(final String p_strId, final IModel<?> p_oLabelModel, final IModel<String> p_oReplacementTextModel)
  {
    super(p_strId, LabelType.TEXT_REPLACEMENT, p_oLabelModel);
    setReplacementText(p_oReplacementTextModel);
  }
}
