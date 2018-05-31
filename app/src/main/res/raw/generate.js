

function Monster() {
    return {
        name: "name",
        size: "medium",
        type: "humanoid",
        alignment: "unaligned",
        ac: "10",
        acType: "(natural)",
        maxHp: 5,
        hd: "1d8",
        speed: "30 ft",
        strength: 10,
        dexterity: 10,
        constitution: 10,
        intelligence: 10,
        wisdom: 10,
        charisma: 10,
        cr: 0,
        xp: 0,
        conditionImmunities: ["no condition immunities"],
        damageImmunities: ["no damage immunities"],
        damageResistances: ["no damage resistances"],
        senses: ["normal vision"],
        languages: ["common"],
        skills: ["athletics +2", "medicine +2"],
        powers: [
            {
                name: "Unknown Power",
                details: "This is the text of some unknown power."
            }
        ],
        actions: [
            {
                name: "dash",
                details: "Gain extra movement equal to it's speed."
            },
            {
                name: "dodge",
                details: "Until the beginning of the next turn, all attacks against you are at disadvantage."
            },
            {
                name: "hide",
                details: "This may only be performed while there is no line of sight. You become hidden."
            },
            {
                name: "slam",
                toHit: 2,
                type: "Melee weapon attack",
                targets: 1,
                reach: "5 ft.",
                onHit: "1d6 + 1 bludgeoning damage",
                onMiss: "1 bludgeoning damage"
            }
        ]
    };
}

// function Monster(
//     name,               				// string
//     size,               				// string
//     type,               				// string
//     alignment,          				// string
//     ac,                 				// number
//     acType,             				// string
//     maxHp,              				// number
//     hd,                 				// string
//     speed,              				// number
//     strength,           				// number
//     dextertity,         				// number
//     constitution,       				// number
//     intelligence,       				// number
//     wisdom,             				// number
//     charisma,           				// number
//     conditionImmunities,    			// array
//     damageImmunities,   				// array
//     damageResistances,                  // array
//     senses,                             // array
//     languages,                          // array
//     skills,                             // array
//     powers,                             // array
//     actions                             // array
//
// ) {
//     return {
//         name: name,
//         size: size,
//         type: type,
//         alignment: alignment,
//         ac: ac,
//         acType: acType,
//         maxHp: maxHp,
//         hd: hd,
//         speed: speed,
//         strength: strength,
//         dextertity: dextertity,
//         constitution: constitution,
//         intelligence: intelligence,
//         wisdom: wisdom,
//         charisma: charisma,
//         conditionImmunities: conditionImmunities,
//         damageImmunities: damageImmunities,
//         damageResistances: damageResistances,
//         senses: senses,
//         languages: languages,
//         skills: skills,
//         powers: powers,
//         actions: actions
//     };
// }

function signed(number) {
    return (number<0) ? ("" + number) : ("+" + number);
}

/**
 * This will return the list of items as a comma seperated string ignoring
 * all content that is empty
 * @param list
 * @returns {string}
 */
function listStr(name, list) {
    try {
        var output = "";
        var added = 0;
        for(i=0; i<list.length; i++) {
            var item = list[i];
            if (item && item.length>0) {
                if (added > 0) {
                    output += ", ";
                }
                output += item;
                added++;
            }
        }
        return output;
    } catch (error) {
        throw new Error("Could not build the list string '" + title + "'\n" + error.message)
    }
}

/**
 * This will format the array into a single content string and then return it's content
 * @param title
 * @param array
 * @returns {string}
 */
function listPropertyLine(title, array) {
    var theLine = listStr(title, array);
    return propertyLine(title, theLine);
}

/**
 * If there is no content then no html is returned.
 * @param title should be the title to display if content exists
 * @param text is the value of the property as a string
 * @returns {string}
 */
function propertyLine(title, text) {
    var html = '';
    if (text && text !== "") {
        html += '<property-line>';
        html += '   <h4>' + title + '</h4>';
        html += '   <p>' + text + '</p>';
        html += '</property-line>';
    }
    return html;
}

/**
 * If there is no content then no html is returned
 * @param title is the expected name of the property or ability
 * @param text is the details of the property as a string
 * @returns {string}
 */
function propertyBlock(title, text) {
    var html = '';
    if (text && text !== "") {
        html += '<property-block>\n';
        html += '   <h4>' + title + '</h4>\n';
        html += '   <p>' + text + '</p>\n';
        html += '</property-block>\n';
    }
    return html;
}

function abilitiesBlock(strength, dexterity, constitution, intelligence, wisdom, charisma) {
    if (strength, dexterity, constitution, intelligence, wisdom, charisma) {
        html = '';
        html += '<abilities-block \n'
        html += '   data-str="' + strength + '"\n';
        html += '   data-dex="' + dexterity + '"\n';
        html += '   data-con="' + constitution + '"\n';
        html += '   data-int="' + intelligence + '"\n';
        html += '   data-wis="' + wisdom + '"\n';
        html += '   data-cha="' + charisma + '">\n'
        html += '</abilities-block>\n';
        return html;
    } else {
        throw new Error("Ability scores not properly defined!");
    }
}

function attackBlock(title, attack) {
    var line = "";
    var toHitText = signed(attack.toHit) + " to hit, "
    var reachText = "reach " + attack.reach + ", ";
    var targetText = (attack.targets<1) ? "" : (attack.targets==1) ? "one target." : "" + attack.targets + " targets";

    line += "<i>" + attack.type + ":</i>&nbsp" + toHitText;
    line += ((attack.reach) ? reachText : "");
    line += ((attack.targets) ? targetText : "");

    // Add the list for hit and miss
    if (attack.onHit || attack.onMiss) {
        var onHitText = (attack.onHit) ? ("<li><i>Hit:</i>&nbsp" + attack.onHit + "") : "</li>";
        var onMissText = (attack.onMiss) ? ("<li><i>Miss:</i>&nbsp" + attack.onMiss + "") : "</li>";
        line += "<ul>" + onHitText + onMissText + "</ul>";
    }
    return propertyBlock(title, line);
// <property-block>
//     <h4>Slam.</h4>
//     <p><i>Melee Weapon Attack:</i> +4 to hit, reach 5 ft., one target.
//     <i>Hit:</i> 5 (1d6 + 2) bludgeoning damage.</p>
//     </property-block>
}

function monsterHtml(monster) {
    // Note some properties should be arrays
    var html = "";
    // html += '<!DOCTYPE html>';
    // html += '    <html>';
    // html += '        <head>';
    // html += '            <meta charset="UTF-8">';
    // html += '                <title>' + monster.name + '</title>';
    // html += '';
    // html += '            <link rel="import" href="src/top-stats.html">';
    // html += '            <link rel="import" href="src/creature-heading.html">';
    // html += '            <link rel="import" href="src/abilities-block.html">';
    // html += '            <link rel="import" href="src/property-block.html">';
    // html += '            <link rel="import" href="src/property-line.html">';
    // html += '            <link rel="import" href="src/stat-block.html">';
    // html += '';
    // html += '            <style>';
    // html += '                body {';
    // html += '                    margin: 0;';
    // html += '                }';
    // html += '';
    // html += '                stat-block {';
    // html += '                    /* A bit of margin for presentation purposes, to show off the drop';
    // html += '                    shadow. */';
    // html += '                    margin-left: 20px;';
    // html += '                    margin-top: 20px;';
    // html += '                }';
    // html += '            </style>';
    // html += '        </head>';
    // html += '        <body>';
    html += '<stat-block>';
    html += '  <creature-heading>';
    html += '    <h1>' + monster.name + '</h1>';
    html += '    <h2>' + monster.size + ' ' + monster.type + ', ' + monster.alignment + '</h2>';
    html += '  </creature-heading>';
    html += '';
    html += '  <top-stats>';
    html += propertyLine("Armor Class", '' + monster.ac + ' (' + monster.acType + ')');
    // html += '    <property-line>';
    // html += '      <h4>Armor Class</h4>';
    // html += '      <p>' + monster.ac + ' (" + monster.acType + ")</p>';
    // html += '    </property-line>';
    html += propertyLine("Hit Points", '' + monster.maxHp + '(' + monster.hd + ')');
    // html += '                <property-line>';
    // html += '                    <h4>Hit Points</h4>';
    // html += '                        <p>' + monster.maxHp + '(' + monster.hd + ')</p>';
    // html += '                </property-line>';
    html += propertyLine("Speed", monster.speed);
    // html += '                <property-line>';
    // html += '                    <h4>Speed</h4>';
    // html += '                    <p>' + monster.speed + '</p>';
    // html += '                </property-line>';
    html += '';

    html += abilitiesBlock(monster.strength, monster.dexterity, monster.constitution, monster.intelligence, monster.wisdom, monster.charisma);

    html += listPropertyLine("Condition Immunities", monster.conditionImmunities);
    html += listPropertyLine("Damage Immunities", monster.damageImmunities);
    html += listPropertyLine("Damage Resistances", monster.damageResistances);
    html += listPropertyLine("Senses", monster.senses);
    html += listPropertyLine("Languages", monster.languages);
    html += listPropertyLine("Skills", monster.skills);

    html += propertyLine("Challenge", "" + monster.cr + " (" + monster.xp + " XP)");
    html += '        </top-stats>';

    for(power of monster.powers) {
        if (power && power.name && power.details) {
            html += propertyBlock(power.name, power.details);
        }
    }
    html += '';

    html += '        <h3>Actions</h3>';

    for(action of monster.actions) {
        if (action && action.name) {

            if (action.toHit) {
                html += attackBlock(action.name, action);
            } else if (action.details) {
                html += propertyBlock(action.name, action.details);
            }
        }
    }
    html += '        </stat-block>';
    // html += '    </body>';
    // html += '</html>';

    console.log("HTML CREATED");
    console.log(html);
    return html;
}




function demoIt() {
    var M = new Monster();
    var result = "not initialized yet";
    // result = "monster<br/><pre>" + monsterHtml(M) + "</pre>";
    result = monsterHtml(M);
    // var result = "HELLO WORLD!";
    var theDiv = document.getElementById("content");
    theDiv.innerHTML = result;
    console.log(result);
}

demoIt();