(ns blockchain.validar_transacao)

(defn valida? [request]
  (let [entradas #{:valor :tipo}]
    (and (contains? request :valor)
        (number? (:valor request))
        (pos? (:valor request))
        (contains? request :tipo)
        (or (= "despesa" (:tipo request))
            (= "receita" (:tipo request))
        (= entradas (set (keys request))))))
      true)