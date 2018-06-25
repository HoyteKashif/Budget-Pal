package com.nmiller.calendar.component.label;

import org.apache.wicket.model.IModel;

/**
 * AutoHideLabel is a class that automatically hides the label
 * if the model backing the label is null or empty.
 */
public class AutoHideLabel extends BaseLabel
{ 
  /** serialVersionUID */
  private static final long serialVersionUID = -5422321306644971725L;

  public AutoHideLabel(final String p_strId)
  {
    this(p_strId, null);
  }
  
  public AutoHideLabel(final String p_strId, final IModel<?> p_oLabelModel)
  {
    super(p_strId, LabelType.AUTO_HIDE, p_oLabelModel);
  }
}
