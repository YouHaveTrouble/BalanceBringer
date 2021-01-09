<h1>BalanceBringer</h1>

<h2>Brings balance to the game</h2>
<h3>(because Mojang apparently can't)</h3>

<h4>This branch is purely for purpur servers.</h4>

BalanceBringer can rid your worlds from pesky, laggy villagers with 
overpowered trades.

    villagers:
      deny-spawn: true
      actively-remove: true

Setting both of those options to true will automatically snap all 
existing villagers on chunk load and prevent spawning of new ones.
This also includes zombie villagers.

<hr>

Mending enchant has been an issue with game balance since it was created.
BalanceBringer allows you to snap it away aswell.

    mending:
      actively-remove: true
      remove-anvil-repair-limit:
        enable: true
        static-cost: 1
      
Setting `mending.actively-remove` to true will make it so all instances of mending enchantment 
will be erased from your world and swapped with unbreaking III. Anywhere. 
Poof, gone. Easy as that.

`remove-anvil-repair-limit` is gonna make sure players can fix their tools using anvil infinitely 
(but they have to have resources to do that).

`static-cost` is how much the base repair cost should be if repair limit is disabled.
