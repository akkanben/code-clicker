# System Requirements

## Vision

### What is the vision of this product?

- The vision of Code Clickers is to create a idle/clicker game coding themed which people can use for entertainment.

### What pain point does this project solve?

- The pain point addressed by the project is to create a game that provides a relaxing atmosphere that encourages return use but does not require constant or intense attention.

### Why should we care about our product?

- Code Clicker helps with users that are reluctant or unable to spend money on phone apps by providing them with an alternative to pay walls so that they can have access to the app in a manner that is sustainable for developers. Code Clickers also tracks the progress for the time elapsed between sessions so that the user can close the app when they are busy and re-open it whenever they are free to play without losing their possible progress.

### What will our product do?

- Interact with the main input button to generate more lines of code (currency).
- Purchase new code-per-second generators from a store using the code lines as currency.
- Purchase upgrades for the generators to make them more effective.
- Evolving art assets.

### What will our product not do?

- Multi-player interaction.
- In-app purchases.

## Minimum Viable Product

### What will our MVP MVP functionality be?

- Tapping Interaction
- Purchasable generators and upgrades
- Increasing scale and range of UI elements
- Frequent UI re-renders (more frequent than 1 per second)
- Allow for very large numbers (scale past MAX_VALUE)
- Android Room save system
- Banner add or other
- Resuming calculates the new balance of lines

## Stretch

### What stretch goals are you going to aim for?

- Graceful end game
- Cloud saves
- Alternate upgrade pathways ("Crunch" vs "WLB")
- More art assets per "upgrade".
- Add sound effects.
- Idle length upgrades (not indefinite)

## Functional Requirements

- A user can purchase generators that increase the current lines-per-second
- A user can purchase upgrades for generators that increase their effectiveness
- A user can purchase upgrades that increase the effectiveness of user clicks
- A user can leave the app and return with out starting over
- A user can display purchasable generators and upgrades and dismiss them from view

## Non-Functional Requirements

Code Clicker will have a suit of tests to ensure the display UI is updated accurately. Increasing and decreasing the line currency will happen frequently and will need to be shown reliably. Generator items from the store will appear visible at certain thresholds, these will be verified with testing. The large numbers involved in idle clickers require special care not to incur a overflow, testing will be required to avoid this.

