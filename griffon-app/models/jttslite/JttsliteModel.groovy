package jttslite

//import org.viewaframework.widget.swing.table.*


import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.DisposableMap
import ca.odell.glazedlists.FunctionList
import ca.odell.glazedlists.SortedList
import ca.odell.glazedlists.TreeList
import org.viewaframework.swing.table.DynamicTableColumn
import org.viewaframework.swing.table.DynamicTableModel
import ca.odell.glazedlists.GlazedLists
import ca.odell.glazedlists.EventList

class JttsliteModel {
    @Bindable boolean inProgress
    @Bindable String status
    @Bindable Long selectedTaskId
    @Bindable Long inProgressWorklogId

    TaskService taskService

    BasicEventList taskList = new BasicEventList ()
    DisposableMap taskMap = GlazedLists.syncEventListToMap(taskList, new TaskKeyMaker ())
    TreeList taskTreeList = new TreeList(new SortedList (taskList, {a,b-> a.key <=> b.key} as Comparator), new TaskTreeFormat(), TreeList.NODES_START_EXPANDED)
    EventList worklogList = new SortedList (new BasicEventList (), {a,b-> a.key <=> b.key} as Comparator)

    /* Table result model using viewaframework.org DynamicTableModel */
    @Newify([DynamicTableColumn])
    def tableModel =
        new DynamicTableModel([
                DynamicTableColumn.new(propertyName:"name",order:0,width:400,title:"Start time"/*,renderer:FileNameRendererByType.new()*/),
                DynamicTableColumn.new(propertyName:"type",order:1,width:100,title:"Finish time"),
                DynamicTableColumn.new(propertyName:"size",order:2,width:100,title:"Duration"),
                DynamicTableColumn.new(propertyName:"path",order:3,width:600,title:"Comment")
        ])

    void mvcGroupInit(Map args) {
        status = "Welcome to ${GriffonNameUtils.capitalize(app.getMessage('application.title'))}"
    }

    private class TaskTreeFormat implements TreeList.Format {
        public void getPath(List path, Object element) {
            def taskPath = taskService.getTaskPath (element.id)

            def elems = taskPath.collect {elem->
                def origElem = taskMap.get(elem.id)
                return origElem
            }
            path.addAll (elems)
        }

        public boolean allowsChildren(Object element) {
            return true;
        }

        @Override
        public Comparator getComparator(int arg0) {
            return null
//            @SuppressWarnings("unchecked")
//            final Comparator comparator = GlazedLists.chainComparators(
////                    GlazedLists.beanPropertyComparator(Location.class, "continent"),
////                    GlazedLists.beanPropertyComparator(Location.class, "country"),
////                    GlazedLists.beanPropertyComparator(Location.class, "province"),
////                    GlazedLists.beanPropertyComparator(Location.class, "city")
//            );
//
//            return comparator;
        }
    }


    class TaskKeyMaker implements FunctionList.Function<Map, Object> {

        Object evaluate(Map sourceValue) {
            return sourceValue.id
        }
    }
}
