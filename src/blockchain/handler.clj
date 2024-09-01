(ns blockchain.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [blockchain.atomobc :refer [criar-bloco ler-bloco]]
            [blockchain.validar_bc :refer [bloco-valido?]]
            [blockchain.atomo_transacao :refer [criar-transacao ler-transacao]]
            [blockchain.validar_transacao :refer [valida?]]
            [cheshire.core :as json]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]))
(defn como-json
  ([content]
   (como-json content 200))
  ([content status]
   {:status status
    :headers {"Content-Type" "application/json; charset=utf-8"}
    :body (json/generate-string content)}))

(defroutes app-routes
  (GET "/ler-bloco" [] (-> (ler-bloco)
                           (como-json)))
  (POST "/criar-bloco" request
    (if (bloco-valido? (:body request)) (-> (criar-bloco (:body request)) (como-json))
        (como-json {:message "Bad Request"} 400)))
  (GET "/ler-transacao" [](-> (ler-transacao) (como-json)))
  (POST "/criar-transacao" request
        (if (valida? (:body request)) (-> (criar-transacao (:body request)) (como-json))
            (como-json {:message "Bad Request"} 400)))
  (route/not-found "Not Found"))

(def app
  (->
   (wrap-defaults app-routes api-defaults)
   (wrap-json-body {:keywords? true :bigdecimals? true})
   (wrap-cors
    :access-control-allow-origin [#".*"]
    :access-control-allow-methods [:get :post :put :delete :options]
    :access-control-allow-headers ["Content-Type" "Authorization"])))