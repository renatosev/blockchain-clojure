(ns blockchain.atomo_transacao
  (:require [blockchain.uuid :refer [random-uuid]]))

(defonce transacoes (atom []))

(defn ler-transacao []
  @transacoes)

(defn criar-transacao [request]
  (let [transacao (assoc request :id (random-uuid))]
    (swap! transacoes conj transacao)
    transacao))
