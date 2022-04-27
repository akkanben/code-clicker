# System Requirements

## Vision

### What is the vision of this product?

- The vision of Code Clicker is to create an idle/clicker game for Android devices that is themed around coding.

### What pain point does this project solve?

- This game provides a relaxing atmosphere that encourages return use but does not require constant or intense attention.

### Why should we care about our product?

- Code Clicker is accessible to users that are reluctant or unable to spend money on phone apps by providing them with an alternative to paywalls so that they can have access to the app in a manner that is sustainable for developers. Code Clicker also tracks the progress for the time elapsed between sessions so that the user can close the app when they are busy and re-open it whenever they are free to play without losing their possible progress.

### What will our product do?

- Interact with the main input button to generate more "lines of code" (currency)
- Purchase new "code-per-second" generators from an in-game store using the code lines as currency
- Purchase upgrades for the generators to make them more effective
- Evolving art assets

### What will our product not do?

- Multi-player interaction
- Real currency in-app purchases

## Minimum Viable Product

### What will our MVP MVP functionality be?

- Tapping interaction
- Purchasable generators and upgrades
- Increasing scale and range of UI elements
- Frequent UI re-renders (more frequent than 1 per second)
- Allow for very large numbers (scale past MAX_VALUE)
- Android Room save system
- Banner ad (or other advertising forms)
- Resuming app calculates the new balance of lines based on time elapsed

## Stretch

### What stretch goals are you going to aim for?

- Graceful end game condition (Kill screen)
- Cloud saves
- Alternate upgrade pathways ("Crunch" vs "WLB")
- More art assets per "upgrade"
- Sound effects
- Upgrade tiers for maximum idle time credited (not indefinite)

## Functional Requirements

- A user can purchase generators that increase the current lines-per-second
- A user can purchase upgrades for generators that increase their effectiveness
- A user can purchase upgrades that increase the effectiveness of user clicks
- A user can leave the app and return without losing progress
- A user can display purchasable generators and upgrades and dismiss them from view

## Non-Functional Requirements

- Code Clicker will have a suite of tests to ensure the display UI is updated accurately
- Increasing and decreasing the line currency will happen frequently and will need to be shown reliably
- Generator items from the store will appear visible at certain thresholds; these will be verified with testing
- The large numbers involved in idle clickers require special care not to incur an overflow; testing will be required to avoid this
