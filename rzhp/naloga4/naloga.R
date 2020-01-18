#U is universal set (usually 1:n)
#n - number of desired subsets
#minSize = minimum subset size
#maxSize = maximum subset size (last set can be an exception)
generateSubsets <- function(U, n, minSize = 1, maxSize = sqrt(length(U))){
  Subsets <- list()
  leftovers <- U
  for(i in 1:(n-1)){
    items <- sample(1:length(U), sample(minSize:maxSize,1))
    Subsets[[i]] <- U[items]
    indexes <- which(leftovers %in% U[items])
    if(length(indexes) != 0){
      leftovers <- leftovers[-which(leftovers %in% U[items])]
    }
  }
  if(length(leftovers) == 0){
    items <- sample(1:length(U), sample(minSize:maxSize,1))
    Subsets[[n]] <- U[items]
  }else{
    #Add all remaning elements if needed
    Subsets[[n]] <- leftovers 
    if(length(Subsets[[n]]) < maxSize) {
      candidates <- U[-which(Subsets[[n]] %in% U)]
      items <- sample(1:length(candidates), sample(1:(maxSize-length(Subsets[[n]])), 1))
      Subsets[[n]] <- c(Subsets[[n]], candidates[items])
    }
  }
  Subsets
}

# Si is a subset of U
# C is a set of items we have covered up to this point
getCostEffectivenes <- function(Si, C) {
  x <- length(setdiff(Si, C)) + 0.0
  if (x == 0) {
    100
  } else {
    length(Si) / x
  }
}

#U is universal set (usually 1:n)
#S is a list of subsets
#returns a list of subsets included in the solution
SCPgreedy <- function(U, S) {
  # C is a set of items that we expand as we select a subset until U - C = empty set
  C <- c()
  # indexes is a vector of indexes of subsets in the solution
  indexes <- integer(length(S))
  
  # we greedily find the most cost effective set each iteration
  while (length(C) < length(U)) {
    bestSet <- 0
    bestSetCostEff <- Inf # infinity, just so an actual set gets selected
    
    # go through all subsets and select the most cost effective one
    for (i in 1:length(S)) {
      # check that the subset isn't already in the solution
      if (indexes[[i]] == 0) {
        costEff <- getCostEffectivenes(S[[i]], C)
        # if the subset is more cost effective than the current best one, select it
        if (costEff < bestSetCostEff) {
          bestSet <- i
          bestSetCostEff <- costEff
        }
      }
    }

    # add the selected subset to C
    C <- union(C, S[[bestSet]])
    # add the index of subsets to indexes
    indexes[[bestSet]] <- 1
  }
  
  print("Indexes of subsets in the solution")
  print(indexes)
  
  S[!!indexes]
}

#U is universal set (usually 1:n)
#S is a list of subsets
#returns a list of subsets included in the solution
SCPlinear <- function(U, S) {
  # Load lpSolveAPI
  require(lpSolveAPI)

  constraintCount <- length(U)
  subsetCount <- length(S)
  
  # Set an empty linear problem
  linProgram <- make.lp(nrow = 0, ncol = subsetCount)
  # Set the linear program as minimization
  lp.control(linProgram, sense="min")
  
  # Set type of decision variables to binary, as this is a binary problem
  set.type(linProgram, 1:subsetCount, type=c("binary"))
  
  # Calculate the length of each subset (that is its cost as defined in the homework instructions)
  # Store the lengths(costs) of each subset into a vector while keeping the same order as the list of subsets
  costs <- rapply(S, length, how="unlist")

  # Set objective function coefficients to the calculated costs
  set.objfn(linProgram, costs)
  
  # Add constraints
  constraints = matrix(integer(constraintCount * subsetCount), nrow = constraintCount, ncol = subsetCount)
  i <- 1 # i (index of subset) is used as column in the matrix
  for (subset in S) {
    for (item in subset) {
      # item (element of subset) is used as the row in the matrix
      constraints[item, i] = 1
    }
    
    i <- i + 1
  }
  
  # add constraints to the lpSolve
  for (row in 1:constraintCount) {
    add.constraint(linProgram, constraints[row, ], ">=", 1)
  }

  # Solve problem
  solve(linProgram)

  # Get the variables
  result <- get.variables(linProgram)
  print("Indexes of subsets in the solution")
  print(result)
  
  S[!!result]
}

U <- 1:10
Subsets <- generateSubsets(U, 13)
Subsets
print("Greedy solution:")
SCPgreedy(U, Subsets)
print("Linear solution:")
SCPlinear(U, Subsets)
