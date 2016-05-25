package com.wk.mobile.money.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.smartgwt.client.data.*;
import com.wk.mobile.base.client.chart.PieOpt;
import com.wk.mobile.base.client.widget.Midget;
import com.wk.mobile.money.client.FAB;
import com.wk.mobile.money.client.ClientFactory;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import org.jetbrains.annotations.NotNull;

import static com.wk.mobile.base.client.widget.Midget.*;

public class DashViewImpl extends MaterialContainer implements DashView {

    protected ClientFactory clientFactory;

    private HTMLPanel panel;
    private HTMLPanel incomeChartPanel;
    private HTMLPanel expenseChartPanel;
    private MaterialLabel totalIncomeLabel;
    private MaterialLabel totalExpensesabel;
    private MaterialLabel periodIncomeLabel;
    private MaterialLabel periodExpenseLabel;
    private MaterialPreLoader expenseSpinner;
    private MaterialPreLoader incomeSpinner;
    private PieChart incomeChart;
    private PieChart expenseChart;


    public DashViewImpl(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        add(createContentBody());
    }

    private IsWidget createContentBody() {
        panel = new HTMLPanel("");
        panel.addStyleName("contentBody");

        createSummaryCharts();

        return panel;
    }

    private void createSummaryCharts() {
        createIncomeSummaryChart();
        createExpenseSummaryChart();

        panel.add(row()
                .add(col().grid("s12 m6 l6")
                        .add(periodIncomeLabel)
                        .add(totalIncomeLabel)
                        .add(incomeChartPanel)
                        .get())
                .add(col().grid("s12 m6 l6")
                        .add(periodExpenseLabel)
                        .add(totalExpensesabel)
                        .add(expenseChartPanel)
                        .get())
                .get());

        reset();
    }

    private void createExpenseSummaryChart() {
        expenseChartPanel = new HTMLPanel("");
        expenseSpinner = createSpinner();
        expenseChartPanel.add(expenseSpinner);
        expenseChart = createChart();
        expenseChartPanel.add(expenseChart);
        totalExpensesabel = label().fontSize(1.3, Style.Unit.EM).textColor("red").get();
        periodExpenseLabel = label()
                .fontSize(0.7, Style.Unit.EM)
                .get();
    }

    private void createIncomeSummaryChart() {
        incomeChartPanel = new HTMLPanel("");
        incomeSpinner = createSpinner();
        incomeChartPanel.add(incomeSpinner);
        incomeChart = createChart();
        incomeChartPanel.add(incomeChart);
        totalIncomeLabel = label().fontSize(1.3, Style.Unit.EM).textColor("blue").get();
        periodIncomeLabel = label()
                .fontSize(0.7, Style.Unit.EM)
                .text(clientFactory.getConstants().income() + " (" + clientFactory.getConstants().last30Days() + ")")
                .get();
    }

    @Override
    public void setIncomeData(final Record[] records) {
        updateSummaryChart(records, 1);
    }

    @Override
    public void reset() {
        totalIncomeLabel.setText("");
        totalExpensesabel.setText("");
        periodExpenseLabel.setText("");
        periodIncomeLabel.setText("");
        incomeSpinner.setVisible(false);
        expenseSpinner.setVisible(false);
        incomeChart.setVisible(false);
        expenseChart.setVisible(false);
    }

    @Override
    public void setExpensesData(final Record[] records) {
        updateSummaryChart(records, -1);
    }

    private void updateSummaryChart(Record[] records, int type) {

        try {
            if (type == -1) { // Expenses
                expenseSpinner.setVisible(true);
            } else {
                incomeSpinner.setVisible(true);
            }


            if (records.length > 0) {

                DataTable dataTable = DataTable.create();
                dataTable.addColumn(ColumnType.STRING, clientFactory.getConstants().category());
                dataTable.addColumn(ColumnType.NUMBER, clientFactory.getConstants().amount());

                float total = 0;

                dataTable.addRows(records.length);
                for (int i = 0; i < records.length; i++) {
                    Record record = records[i];
                    dataTable.setValue(i, 0, record.getAttribute("category_name"));
                    dataTable.setValue(i, 1, record.getAttribute("amount") != null ? record.getAttributeAsFloat("amount") : 0);

                    total += (record.getAttribute("amount") != null ? record.getAttributeAsFloat("amount") : 0);
                }


                PieOpt opt = new PieOpt();
/*
                if (type == -1) { // Expenses
                    opt.setColors("ff9800", "ffa726", "ffb74d", "ffcc80", "ffe0b2", "fff3e0", "e65100", "ef6c00", "f57c00", "fb8c00");
                }
                else {
                    opt.setColors("4caf50", "66bb6a", "81c784", "a5d6a7", "c8e6c9", "e8f5e9", "1b5e20", "2e7d32", "388e3c", "43a047");
                }
*/
                opt.setIs3D(true);



                if (type == -1) { // Expenses
                    totalExpensesabel.setText(NumberFormat.getFormat("0.00").format(total));
                    periodExpenseLabel.setText(clientFactory.getConstants().expenses() + " (" + clientFactory.getConstants().last30Days() + ")");
                    expenseChart.setVisible(true);
                    expenseChart.draw(dataTable, opt.get());
                } else {
                    totalIncomeLabel.setText(NumberFormat.getFormat("0.00").format(total));
                    periodIncomeLabel.setText(clientFactory.getConstants().income() + " (" + clientFactory.getConstants().last30Days() + ")");
                    incomeChart.setVisible(true);
                    incomeChart.draw(dataTable, opt.get());
                }

            }
        }
        finally {
            if (type == -1) { // Expenses
                expenseSpinner.setVisible(false);
            }
            else {
                incomeSpinner.setVisible(false);
            }
        }

    }

    @NotNull
    private PieChart createChart() {
        PieChart chart = new PieChart();
        chart.setWidth("100%");
        chart.setHeight("100%");
        return chart;
    }

    @NotNull
    private MaterialPreLoader createSpinner() {
        MaterialSpinner spinner = new MaterialSpinner();
        MaterialPreLoader materialPreLoader = new MaterialPreLoader();
        materialPreLoader.add(spinner);
        return materialPreLoader;
    }

    private void createAccountsCollapsible(Record[] records) {
        Midget<MaterialCollapsible> collapsible = collapsible().type(CollapsibleType.POPOUT);
        for (Record record : records) {
            collapsible.add(collapsibleItem()
                    .add(collapsibleHeader().add(link().text(record.getAttribute("name")).iconType(IconType.CREDIT_CARD).iconPosition(IconPosition.LEFT).get()).get())
                    .add(collapsibleBody().add(label().text(record.getAttribute("name")).get()).get())
                    .get());
        }
        panel.add(collapsible.get());
    }

    public void updateFAB() {
        clientFactory.getFAB().clear();
        clientFactory.getFAB().add(FAB.createTransactionAddFABItem(0, clientFactory));
        clientFactory.getFAB().add(fabList()
                .add(FAB.createAccountAddFABItem(2, clientFactory))
                .add(FAB.createCategoryAddFABItem(1, clientFactory))
                .get());
    }



}
