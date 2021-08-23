<h1>BalanceBringer</h1>

<h2>Brings balance to the game</h2>
<h3>(because Mojang apparently can't)</h3>

<h4>This plugin is purely for <a href="https://purpur.pl3x.net/">purpur</a> servers.</h4>

BalanceBringer can rid your worlds from pesky, laggy villagers with 
overpowered trades.

    villagers:
      actively-remove: true

Setting this option to true will automatically snap all 
existing villagers on chunk load and prevent spawning of new ones.
This also includes zombie villagers.

<hr>

Mending enchant has been an issue with game balance since it was created.
BalanceBringer allows you to snap it away aswell.

    mending:
      actively-remove: true
      remove-anvil-repair-limit:
        enable: true
        max-cost: 35
      
Setting `mending.actively-remove` to true will make it so all instances of mending enchantment 
will be erased from your world and swapped with unbreaking III. Anywhere. 
Poof, gone. Easy as that.
