(ns blockchain.validar_bc)

(defn bloco-valido? [request]
  (let [entradas #{:data :transacoes}]
    (and (contains? request :data)
         (contains? request :transacoes)
         (vector? (:transacoes request))
         (= entradas (set (keys request))))))
