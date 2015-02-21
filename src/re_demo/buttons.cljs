(ns re-demo.button
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [re-com.core      :refer [label spinner]]
            [re-com.buttons   :refer [button button-args-desc]]
            [re-com.box :refer [h-box v-box box gap line]]
            [re-demo.utils    :refer [panel-title component-title args-table]]
            [reagent.core     :as    reagent]))


(def state (reagent/atom
             {:outcome-index 0
              :see-spinner  false}))

(def click-outcomes
  [""   ;; start blank
   "Nuclear warhead launched."
   "Oops. Priceless Ming Vase smashed!!"
   "Diamonds accidentally flushed."
   "Toy disabled"])


(defn button-demo
  []
  [v-box
   :gap      "10px"
   :children [[panel-title "[button ... ]"]

              [h-box
               :gap      "50px"
               :children [[v-box
                           :gap      "10px"
                           :width    "450px"
                           :children [[args-table button-args-desc]]]
                          [v-box
                           :gap      "10px"
                           :children [[component-title "Demo"]
                                      [v-box
                                       :children [[h-box
                                                   :children [[button
                                                               :label            "No Clicking!"
                                                               :tooltip          "Seriously, NO CLICKING!"
                                                               :tooltip-position :below-center
                                                               :disabled?         (= (:outcome-index @state) (dec (count click-outcomes)))
                                                               :on-click          #(swap! state update-in [:outcome-index] inc)
                                                               :class             "btn-danger"]
                                                              [box
                                                               :align :center      ;; note: centered text wrt the button
                                                               :child  [label
                                                                        :label (nth click-outcomes (:outcome-index @state))
                                                                        :style {:margin-left "15px"}]]]]
                                                  [gap :size "40px"]
                                                  [h-box
                                                   :gap "50px"
                                                   :children [[button
                                                               :label             (if (:see-spinner @state)  "Stop it!" "See Spinner")
                                                               :tooltip           "I'm a tooltip on the left"
                                                               :tooltip-position :left-center
                                                               :on-click          #(swap! state update-in [:see-spinner] not)]
                                                              (when (:see-spinner @state)  [spinner])]]]]]]]]]])


(defn panel   ;; Only required for Reagent to update panel2 when figwheel pushes changes to the browser
  []
  [button-demo])
