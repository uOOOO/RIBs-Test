package com.uoooo.ribs.test.sub

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SubBuilder.SubScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SubRouter(
    view: SubView,
    interactor: SubInteractor,
    component: SubBuilder.Component) : ViewRouter<SubView, SubInteractor, SubBuilder.Component>(view, interactor, component)
