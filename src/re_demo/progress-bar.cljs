(ns re-demo.progress-bar
  (:require [re-com.core      :refer [progress-bar progress-bar-args-desc slider label checkbox]]
            [re-com.box       :refer [h-box v-box box gap line]]
            [re-demo.utils    :refer [panel-title component-title args-table]]
            [reagent.core     :as    reagent]))


(defn progress-bar-demo
  []
  (let [progress (reagent/atom 75)
        striped? (reagent/atom false)]
    (fn
      []
      [v-box
       :gap "10px"
       :children [[panel-title "[progress-bar ... ]"]
                  [h-box
                   :gap      "50px"
                   :children [[v-box
                               :gap      "10px"
                               :width    "450px"
                               :children [[args-table progress-bar-args-desc]]]
                              [v-box
                               :gap      "10px"
                               :children [[component-title "Demo"]
                                          [v-box
                                           :gap      "20px"
                                           :children [[progress-bar
                                                       :model    progress
                                                       :width    "350px"
                                                       :striped? @striped?]
                                                      [h-box
                                                       :gap "10px"
                                                       :children [[label :label "Percent:"]
                                                                  [slider
                                                                   :model     progress
                                                                   :min       0
                                                                   :max       100
                                                                   :width     "200px"
                                                                   :on-change #(reset! progress %)]
                                                                  [label :label @progress]]]
                                                      [checkbox
                                                       :label     ":striped?"
                                                       :model     striped?
                                                       :on-change #(reset! striped? %)]]]]]]]]])))


(defn panel   ;; Only required for Reagent to update panel2 when figwheel pushes changes to the browser
  []
  [progress-bar-demo])
