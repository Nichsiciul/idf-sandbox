package com.candorgrc.idfusion.sandbox.client.filter;

import java.util.function.Predicate;

import com.candorgrc.idfusion.sandbox.client.model.PersonJSO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLSelectElement;

/**
 *
 */
public class Filter extends Composite  {

	private static FilterUiBinder uiBinder = GWT.create(FilterUiBinder.class);

	interface FilterUiBinder extends UiBinder<HTMLPanel, Filter> {
	}

	@UiField
	protected HTMLInputElement firstName;

	@UiField
	protected HTMLInputElement lastName;

	@UiField
	protected HTMLSelectElement title;

	@UiField
	protected HTMLSelectElement gender;

	@UiField
	protected HTMLSelectElement suffix;

	@UiField
	protected HTMLInputElement race;

	@UiField
	protected HTMLInputElement language;

	@UiField
	protected HTMLInputElement university;

	@UiField
	protected HTMLInputElement buzzword;

	@UiField
	protected HTMLInputElement email;

	@UiField
	protected HTMLInputElement jobTitle;

	@UiField
	protected HTMLInputElement skill;

	@UiField
	protected HTMLInputElement company;

	@UiField
	protected HTMLInputElement department;

	@UiField
	protected HTMLInputElement ein;

	@UiField
	protected HTMLInputElement startDate;

	@UiField
	protected HTMLInputElement endDate;

	/**
	 * Because this class has a default constructor, it can be used as a binder
	 * template. In other words, it can be used in other *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder" xmlns:g=
	 * "urn:import:**user's package**" >
	 * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement
	 * HasHTML instead of HasText.
	 */
	public Filter() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents() {
		addEventHandlers();
	}

	private void addEventHandlers() {
	}

	
	public HTMLInputElement getFirstName() {
		return firstName;
	}

	
	public HTMLInputElement getLastName() {
		return lastName;
	}
	
	
	public HTMLSelectElement getPersonTitle() {
		return title;
	}

	
	public HTMLSelectElement getGender() {
		return gender;
	}

	
	public HTMLSelectElement getSuffix() {
		return suffix;
	}

	
	public HTMLInputElement getRace() {
		return race;
	}

	
	public HTMLInputElement getLanguage() {
		return language;
	}

	
	public HTMLInputElement getUniversity() {
		return university;
	}

	
	public HTMLInputElement getBuzzword() {
		return buzzword;
	}

	
	public HTMLInputElement getEmail() {
		return email;
	}

	
	public HTMLInputElement getJobTitle() {
		return jobTitle;
	}

	
	public HTMLInputElement getSkill() {
		return skill;
	}

	
	public HTMLInputElement getCompany() {
		return company;
	}

	
	public HTMLInputElement getDepartment() {
		return department;
	}

	
	public HTMLInputElement getEin() {
		return ein;
	}

	
	public HTMLInputElement getStartDate() {
		return startDate;
	}

	
	public HTMLInputElement getEndDate() {
		return endDate;
	}
	
	public static Predicate<PersonJSO> toPredicate(Filter view) {
		Predicate<PersonJSO> filter = p -> p.getFirstName().contains(view.getFirstName().value);
		filter = filter.and(p -> p.getLastName().contains(view.getLastName().value));

		final String title = view.getPersonTitle().value;
		if (!title.isEmpty()) {
			// trim ending dot .
			final String filterTitle = title.substring(0, title.length() - 1);
			filter = filter.and(p -> p.getTitle().equals(filterTitle));
		}

		final String suffix = view.getSuffix().value;
		if (!suffix.isEmpty()) {
			filter = filter.and(p -> p.getSuffix().equals(suffix));
		}

		filter = filter.and(p -> p.getGender().toLowerCase().startsWith(view.getGender().value));
		filter = filter.and(p -> p.getRace().contains(view.getRace().value));
		filter = filter.and(p -> p.getLanguage().contains(view.getLanguage().value));
		filter = filter.and(p -> p.getUniversity().contains(view.getUniversity().value));
		filter = filter.and(p -> p.getBuzzword().contains(view.getBuzzword().value));
		filter = filter.and(p -> p.getEmail().contains(view.getEmail().value));
		filter = filter.and(p -> p.getJobTitle().contains(view.getJobTitle().value));
		filter = filter.and(p -> p.getLinkedinSkill().contains(view.getSkill().value));
		filter = filter.and(p -> p.getCompany().contains(view.getCompany().value));
		filter = filter.and(p -> p.getDepartment().contains(view.getDepartment().value));
		filter = filter.and(p -> p.getEin().contains(view.getEin().value));

		return filter;
	}

}
