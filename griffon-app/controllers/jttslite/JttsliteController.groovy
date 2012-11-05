package jttslite

class JttsliteController {

    def taskService
    def workspaceService

    def newAction = {
    }

    def openAction = {
    }

    def saveAction = {
    }

    def saveAsAction = {
    }

    def aboutAction = {
        withMVCGroup('about') { m, v, c ->
            c.show()
        }
    }

    def preferencesAction = {
        withMVCGroup('preferences') { m, v, c ->
            c.show()
        }
    }

    def quitAction = {
        app.shutdown()
    }

    def undoAction = {
    }

    def redoAction = {
    }

    def cutAction = {
    }

    def copyAction = {
    }

    def pasteAction = {
    }

    def deleteAction = {
    }

    def onOSXAbout = { app ->
        withMVCGroup('about') { m, v, c ->
            c.show()
        }
    }

    def onOSXQuit = { app ->
        quitAction()
    }

    def onOSXPrefs = { app ->
        withMVCGroup('preferences') { m, v, c ->
            c.show()
        }
    }

    // these will be injected by Griffon
    def model
    def view

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    /*
        Remember that actions will be called outside of the UI thread
        by default. You can change this setting of course.
        Please read chapter 9 of the Griffon Guide to know more.

    def action = { evt = null ->
    }
    */
    def startProgress = { evt = null ->
        app.event('StartProgress')
    }
    def stopProgress = { evt = null ->
        app.event('StopProgress')
    }

    def onStartProgress = { evt = null ->
        model.inProgress = true
    }
    def onStopProgress = { evt = null ->
        model.inProgress = false
    }


    def onStartupEnd = {app->
        withSql {dataSourcename, sql->
            def tmpList = []
            sql.eachRow ('SELECT * FROM task') {
                tmpList << [id: it.id, workspaceid: it.workspaceId, parentId: it.parentId, siblingIndex:it.siblingIndex, treeCode: it.treeCode, title: it.title, description: it.description]
            }
            edt{model.taskList.addAll (tmpList)}
        }
    }

//    def whenSpringReadyEnd = {app, applicationContext->
//        workspaceService.doInsert ("Default", "Default workspace")
//    }
}
