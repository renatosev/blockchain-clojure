(ns blockchain.uuid)

(defn random-uuid []
  (str (java.util.UUID/randomUUID)))