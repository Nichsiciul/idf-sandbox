package com.candorgrc.idfusion.sandbox.client.view;

import com.candorgrc.idfusion.sandbox.client.datapresentation.PersonCellList;
import com.candorgrc.idfusion.sandbox.client.filter.Filter;
import com.candorgrc.idfusion.sandbox.client.presenter.PersonPresenter;
import com.google.gwt.user.client.ui.IsWidget;

import elemental2.dom.HTMLButtonElement;

/**
 * @author bp
 *
 */
public interface PersonView extends IsWidget {

	void setPresenter(PersonPresenter presenter);

	PersonCellList getPersonList();
	
	HTMLButtonElement getFetch();
	
	Filter getFilter();
}
