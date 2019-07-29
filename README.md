# SR1ePayDataApp
----------------------------------------------------
LEGAL

This software is released under the terms of the Creative Commons Attribution-ShareAlike License, found here: https://creativecommons.org/licenses/by-sa/2.0/

This software implements some systems of the Shadowrun First Edition core rulebook. Please don't sue me.

NO WARRANTY

(I have to say this in order to feel safe publishing.) I make no claims of operability, nor do I make any representation that the software is harmless. I cannot guarantee that no harm will come to you, your data, or your possessions, as a result of installing this software. Installing and/or using this app may ruin your life. 

----------------------------------------------------
WHAT IS THIS

So far, this simple app only calculates Shadowrun 1st Edition full autofire results against a single target. 

HOUSE RULE: In this version, the recoil modifier is progressive, instead of the flat penalty from the rulebook. Say you fire 5 shots; the rulebook says every shot suffers a +5 TN recoil penalty. Instead of that, I have the 1st shot suffering no recoil; the 2nd shot suffering +1 TN recoil, the 3rd shot suffering a +2 recoil mod, etc.

----------------------------------------------------
HOW TO INSTALL
- Download the installer to your Android device by pointing its web browser at: https://github.com/NathanHawks/SR1ePayDataApp/raw/master/app/release/sr1epda-0_1.apk
- Make the serious life decision whether or not to allow some random person's unsigned app to run on your device. Yeah, sorry, I tried to do a signature but it didn't work. Future versions will be signed, eventually.

----------------------------------------------------
IF IT WON'T INSTALL

If it won't install, open an issue at https://github.com/NathanHawks/SR1ePayDataApp/issues containing a screenshot of your device's software version information.

----------------------------------------------------
HOW TO USE

Step 1: Setup
- Leave the Profile field blank (it does nothing)
- Enter the shooter's firearm skill/concentration/specialization rank in the Atk Rank field.
- Enter the target's Body stat in the Def Body field.
- Enter the number of shots to be fired in a single burst of autofire. (The result screen will automatically enforce the max 7 rule, and the max Skill Rank + 1 rule)
- Enter the base TN from the range table.
- Enter the sum of relevant Target Modifiers.
- Enter the weapon's base damage code. I'm sorry the drop-down menu for power level is black on dark grey.
- Enter target's dermal armor rating (if you didn't include it when entering Def Body)
- Enter target's best ballistic armor rating for their worn armor.
- Tap "FIRE!"

Step 2: Results
- If the player is the target, ask the player how they want to use their Dodge Pool; follow your table's usual Dodge Pool rules as to whether the player must use guesswork and strategy in this, or is allowed to simply asign Dodge successes to shooter successes.
- For any shot that isn't being affected by Dodge Dice, simply apply the result to the target!
- Tap the "media play" button to roll again using this setup.

----------------------------------------------------
FUTURE UPDATES (MIGHT NEVER HAPPEN)

I'm not promising I'll ever work on this again since it already does most of what I need; but if I do, here are some features that might pull me back in:

- FIX: Fix the style on the drop-down menu for damage power level (it's more complicated than it sounds)
- ENHANCEMENT: Create a popup to let you pick target modifiers from a list (while alternately still allowing you to manually enter a number)
- FEATURE: Grenade throw resolution.
- FEATURE: Saved setups (the "profile" field, which currently does nothing, was preparation for this) so that you can handle numerous decisions using autofire in the same combat without re-entering data. E.g. a dashboard of buttons to pick the shooter and target.
- FEATURE: PCs and NPCs in a database you can build and edit, so you can quickly set up a combat.
- LAWSUIT: Honestly it could become an entire combat handler but that depends on whether the C&D's start flowing or not (and also I wander away from projects, it's a thing). Autofire requires the full fire combat rules in place, so that's already done, it's just done in a way that can only be used for autofire.
