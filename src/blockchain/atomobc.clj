(ns blockchain.atomobc
  (:require [blockchain.pow :refer [proof-of-work]]
            [blockchain.sha256 :refer [sha-256]]
            [blockchain.atomo_transacao :refer [ler-transacao criar-transacao]]))

(defonce blocos (atom []))
(defonce id-inc (atom -1))

(defn incrementar-id []
  (swap! id-inc inc))

(defn ultimo-bloco []
  (last @blocos))

(defn hash-anterior []
  (if (empty? @blocos)
    "0"
    (:hash (ultimo-bloco))))

(defn criar-bloco [dados]
  (let [transacao (ler-transacao)
        bloco-id (incrementar-id)
        bloco-dados {:numero-bloco @id-inc
                     :transacoes transacao
                     :prev-hash (hash-anterior)}
        proof (proof-of-work bloco-dados 4)
        bloco (assoc bloco-dados :nonce (:nonce proof) :hash (:hash proof))]
    (swap! blocos conj bloco)
    bloco))

(defn ler-bloco []
  @blocos)

(when (empty? @blocos)
  (criar-bloco {}))
