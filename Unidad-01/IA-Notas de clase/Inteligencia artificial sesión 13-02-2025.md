
# Heurística

## Toma de decisiones:
- Basado en experiencia
- Consultar a un experto
- Lo que mejor parezca (criterio)
- Al azar
### Problema de la dieta 
Enunciado: Minimizar el costo de alimentar a las tropas en el campo de batalla sin dejar de ofrecer una dieta saludable. 
Encontrar la combinación (de costo mínimo) de 77 alimentos que permita satisfacer nueve requerimientos nutricionales básicos de una persona de peso promedio.
**Solución**: George Stigler en 1993, propuso una solución utilizando un método heurístico = 39.93 dolares por año.
- Elimino 62 de 77 alimentos con el menor número de nutrientes (pre-procesado).
- Calculó los costos de de todas las combinaciones de alimentos y seleccionó la de menor costo (búsqueda exhaustiva o de fuerza bruta).
## Investigación de Operaciones 
Métodos para solución de problemas: 
- Encontrar variables relevantes
- Encontrar relaciones relevantes
	Dan como resultado un 
	- Modelo cuantitativo 
	- Métodos o algoritmos 
		- Dan solución al modelo
### Método Simplex de Dantzig
- Resuelve problemas modelados como ecuaciones lineales. 
- El problema de la dieta fue de los primeros resueltos por el método simplex.
- Es considerado uno de los 10 algoritmos más impactantes del siglo XX
---
## Métodos de solución 
### Problema de la Dieta
- Método exhaustivo: Stigler(1933)
- Método Simplex Dantzig (1947)
### Problema de ordenamiento
- Bubble sort Friend (1956) ?
- Quick sort Hoare (1960)
- Merge sort Von Neumann (1945)

### Criterios de selección
- Rapidez de codificación 
- Eficiencia 
- Facilidad de entendimiento
---
## Análisis de algoritmos 
### Recursos analizados 
- Tiempo de ejecución 
- Espacio ocupado 
### Variables analizadas 
- Velocidad del procesador 
- Calidad del compilador 
- Calidad del programa 
- Cantidad de datos (entrada)
### Esquema de medición 
- Experimental 
- Teórico
#### Medición experimental 
- Es necesario implementar el algoritmo 
- Depende del software y el hardware 
- No podemos probar con todas las entradas 
#### Estimación Matemática 
- Se asocia una función f(entrada) al algoritmo 
- Se usa el peor caso para caracterizar el tiempo de ejecución 
- Interesa la velocidad de crecimiento del tiempo de ejecución en función del tamaño de la entrada
