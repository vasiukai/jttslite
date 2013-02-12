application {
    title = 'Jttslite'
    startupGroups = ['jttslite']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "preferences"
    'preferences' {
        model      = 'jttslite.PreferencesModel'
        view       = 'jttslite.PreferencesView'
        controller = 'jttslite.DialogController'
    }

    // MVC Group for "license"
    'license' {
        model      = 'jttslite.LicenseModel'
        view       = 'jttslite.LicenseView'
        controller = 'jttslite.DialogController'
    }

    // MVC Group for "credits"
    'credits' {
        model      = 'jttslite.CreditsModel'
        view       = 'jttslite.CreditsView'
        controller = 'jttslite.DialogController'
    }

    // MVC Group for "about"
    'about' {
        model      = 'jttslite.AboutModel'
        view       = 'jttslite.AboutView'
        controller = 'jttslite.DialogController'
    }

    // MVC Group for "jttslite"
    'jttslite' {
        model      = 'jttslite.JttsliteModel'
        view       = 'jttslite.JttsliteView'
        controller = 'jttslite.JttsliteController'
    }

    // MVC Group for "jttslite"
    'workspaces' {
        model      = 'jttslite.WorkspacesModel'
        view       = 'jttslite.WorkspacesView'
        controller = 'jttslite.WorkspacesController'
    }

}
