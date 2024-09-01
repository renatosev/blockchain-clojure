(ns blockchain.pow
  (:require [blockchain.sha256 :refer [sha-256]]))

(defn proof-of-work [dados dificuldade]
  (let [valor (apply str (repeat dificuldade "0"))]
    (loop [nonce 0]
      (let [hash (sha-256 (str nonce dados))]
        (if (.startsWith hash valor)
          {:nonce nonce :hash hash}
          (recur (inc nonce)))))))
