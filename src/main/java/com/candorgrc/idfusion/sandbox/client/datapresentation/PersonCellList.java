package com.candorgrc.idfusion.sandbox.client.datapresentation;

import java.util.function.Predicate;

import com.candorgrc.idfusion.sandbox.client.datapresentation.cell.PersonCell;
import com.candorgrc.idfusion.sandbox.client.datapresentation.cell.PersonCell.Action;
import com.candorgrc.idfusion.sandbox.client.datapresentation.style.CellListResources;
import com.candorgrc.idfusion.sandbox.client.inject.AppGinjector;
import com.candorgrc.idfusion.sandbox.client.model.PersonJSO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;

public class PersonCellList extends AsyncCellList<PersonJSO> {

	/**
	 * eof := true when all current items have been fetched
	 */
	private boolean eof = false;

	private Predicate<PersonJSO> filter;

	private long countFetchPersons;

	/**
	 * Construct a new {@link PersonCellList}
	 */
	protected PersonCellList() {
		// instantiate
		super(new PersonCell(), (CellList.Resources) GWT.create(CellListResources.class), null, 25);

		// bind, e.g. initialize
		bind();

	}

	private void bind() {
		setFilter(person -> true);
		/**
		 * Create a {@link AsyncDataProvider} instance and set it as data source for
		 * this {@link PersonCellList}
		 */
		setDataProvider(new AsyncDataProvider<PersonJSO>() {

			@Override
			protected void onRangeChanged(final HasData<PersonJSO> display) {
				/**
				 * On Range Changed Event: fetch and append the next chunk of {@link PersonJSO}
				 * items
				 */
				refreshData();
			}
		});

		// set value updater
		setValueUpdater(value -> onValueUpdate(value));

		// no selection model & disable SelectionChangeEvent
		setSelectionModel(new NoSelectionModel<PersonJSO>(),
				DefaultSelectionEventManager.<PersonJSO>createWhitelistManager());

		// disable keyboard selection/navigation
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);

	}

	/*
	 * Refresh data: fetch and append next data chunk.
	 *
	 */
	protected void refreshData() {
		/* refresh presenter data */
		HasDataUtils.refreshPresenter(this,
				AppGinjector.INSTANCE.getDataManager().fetchPersons(getOffset(), getRangeSize(), this.filter),
				getVisibleItemCount(), 100);

		/* increment offset */
		incrementOffset();
		// check EOF status
		if (getVisibleItemCount() >= this.countFetchPersons) {
			eof = true;
			GWT.log("You reached EOF");
		}
	}

	/**
	 * Handles cell update events.
	 *
	 * @param handler {@link PersonJSO} instance
	 */
	private void onValueUpdate(PersonJSO handler) {
		final String id = handler.getTitle() + " " + handler.getFirstName() + " " + handler.getLastName();
		// handle EDIT event
		if (Action.UPDATE.name() == handler.getAction()) {
			Window.alert("Update " + id);
		}
		// handle COPY event
		else if (Action.COPY.name() == handler.getAction()) {
			Window.alert("Copy " + id);
		}
		// handle DELETE event
		else if (Action.DELETE.name() == handler.getAction()) {
			Window.alert("Delete " + id);
		}
	}

	/**
	 * Set the visible {@link Range} to <em>first/0</em>, clear presenter data, fire
	 * a {@link RangeChangeEvent} that will be handled by {@link #refreshData()} and
	 * reset offset.
	 *
	 */
	public void resetVisibleRangeAndClearData() {
		// reset EOF
		eof = false;

		super.resetVisibleRangeAndClearData();
	}

	public void refreshWith(Predicate<PersonJSO> filter) {
		setFilter(filter);
		resetVisibleRangeAndClearData();
	}

	private void setFilter(Predicate<PersonJSO> filter) {
		if (filter == null)
			this.filter = person -> true;
		else {
			this.filter = filter;
		}
		this.countFetchPersons = AppGinjector.INSTANCE.getDataManager().countFetchPersons(this.filter);
	}

	public boolean isEof() {
		return eof;
	}
}
