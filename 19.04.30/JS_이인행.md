# Á¤±ÔÇ¥Çö½Ä
- ¹®ÀÚÀÇ ÆÐÅÏÀ» ³ªÅ¸³»´Â °´Ã¼
- ÀÚ¹Ù½ºÅ©¸³Æ®¿¡¼­ RegExp °´Ã¼·Î Ç¥ÇöµÈ´Ù.
- RegExp() »ý¼ºÀÚ·Î ¸¸µé ¼ö ÀÖÁö¸¸, ¸®ÅÍ·² ¹®¹ýÀÌ ´õ ÀÚÁÖ »ç¿ëµÈ´Ù.
- Á¤±Ô Ç¥Çö½Ä ¸®ÅÍ·²Àº ÇÑ ½ÖÀÇ ½½·¡½Ã ¹®ÀÚ »çÀÌ¿¡ À§Ä¡ÇÑ ¹®ÀÚµéÀÌ´Ù.
```js
var pattern = /s$/;
// var pattern = new RegExp("s$"); ¿Í °°Àº ÀÇ¹Ì
// s·Î ³¡³ª´Â ¸ðµç ¹®ÀÚ¿­°ú ¸ÅÄ¡µÇ¸ç, ÇØ´ç ÆÐÅÏµé¿¡ ´ëÇØ¼­´Â µÚ¿¡¼­ ¾Ë¾Æº»´Ù.
```

### ¸®ÅÍ·² ¹®ÀÚ
- ¹®ÀÚ, ¼ýÀÚ, ¿µ¹®ÀÚ : ¹®ÀÚ ±× ÀÚ½Å
- **\0** : NULL ¹®ÀÚ
- **\t** : ÅÇ
- **\n** : ÁÙ¹Ù²Þ
- **\v** : ¼öÁ÷ ÅÇ
- **\f** : Æû ÇÇµå
- **\r** : Ä³¸®Áö ¸®ÅÏ
- **\xnn** : 16Áø¼ö nnÀ¸·Î ¸í½ÃµÈ ¶óÆ¾ ¹®ÀÚ
- **\uxxxx** : 16Áø¼ö xxxx·Î ¸í½ÃµÈ À¯´ÏÄÚµå ¹®ÀÚ
- **\cX** : Á¦¾î ¹®ÀÚ ^X

### ¹®ÀÚ Å¬·¡½º
- ÇØ´ç Å¬·¡½º ³»ÀÇ ¸ðµç ¹®ÀÚ¿¡ ¸ÅÄ¡µÈ´Ù.
- **[...]** : ´ë°ýÈ£ »çÀÌ¿¡ ÀÖ´Â ÇÑ ¹®ÀÚ
- **[^...]** : ´ë°ýÈ£ »çÀÌ¿¡ ¾ø´Â ÇÑ ¹®ÀÚ
- **.** : ÁÙ¹Ù²Þ ¹®ÀÚ³ª À¯´ÏÄÚµå ¶óÀÎ Á¾·á ¹®ÀÚ¸¦ Á¦¿ÜÇÑ ¸ðµç ¹®ÀÚ
- **\w** : ASCII ¿öµå ¹®ÀÚ. **[a-zA-Z0-9]**¿Í µ¿µî
- **\W** : ASCII ¿öµå ¹®ÀÚ°¡ ¾Æ´Ñ ¸ðµç ¹®ÀÚ. **[^a-zA-Z0-9]**¿Í µ¿µî
- **\s** : ¸ðµç À¯´ÏÄÚµå °ø¹é ¹®ÀÚ
- **\S** : À¯´ÏÄÚµå °ø¹é ¹®ÀÚ°¡ ¾Æ´Ñ ¸ðµç ¹®ÀÚ. ÇÏÁö¸¸ **\w**¿Í **\S**´Â °°Àº °ÍÀÌ ¾Æ´Ï´Ù.
- **\d** : ¸ðµç ASCII ¼ýÀÚ. **[0-9]**¿Í µ¿µî
- **\D** : ASCII ¼ýÀÚ°¡ ¾Æ´Ñ ¸ðµç ¹®ÀÚ. **[^0-9]**¿Í µ¿µî
- **[\b]** : ¹é½ºÆäÀÌ½º ¸®ÅÍ·² (Æ¯¼öÇÑ °æ¿ì)

### ¹Ýº¹
- ¹Ýº¹À» Ç¥ÇöÇÏ±â À§ÇÑ Æ¯¼ö ¹®ÀÚ
- **{n,m}** : ¾ÕÀÇ Ç×¸ñÀÌ Àû¾îµµ n¹ø ÀÌ»ó, m¹ø ÀÌÇÏ·Î ³ªÅ¸³­´Ù.
- **{n,}** : ¾ÕÀÇ Ç×¸ñÀÌ n¹ø ÀÌ»ó ³ªÅ¸³­´Ù.
- **{n}** : ¾ÕÀÇ Ç×¸ñÀÌ Á¤È®ÇÏ°Ô n¹ø ³ªÅ¸³­´Ù.
- **?** : ¾ÕÀÇ Ç×¸ñÀÌ 0 ¶Ç´Â ÇÑ ¹ø ³ªÅ¸³­´Ù. Áï, **{0,1}**°ú µ¿µîÇÏ´Ù.
- **+** : ¾ÕÀÇ Ç×¸ñÀÌ ÇÑ ¹ø ÀÌ»ó ³ªÅ¸³­´Ù. Áï, **{1,}**°ú µ¿µîÇÏ´Ù.
- ***** : ¾ÕÀÇ Ç×¸ñÀÌ 0¹ø ¶Ç´Â ±× ÀÌ»ó ³ªÅ¸³­´Ù. Áï, **{0,}**°ú µ¿µîÇÏ´Ù.

### °ýÈ£
- »ý·« °¡´ÉÇÑ ¹®ÀÚ¿­°ú ¸ÅÄ¡
```js
/java(script)?/
// java¿Í ±× ´ÙÀ½¿¡ »ý·« °¡´ÉÇÑ script°¡ ¿À´Â ¹®ÀÚ¿­°ú ¸ÅÄ¡
```
- ¿©·¯ Ç×¸ñÀ» ÇÏ³ªÀÇ ºÎºÐ Ç¥Çö½ÄÀ¸·Î ±×·ìÈ­ÇÏ¿© ´ÜÀ§·Î Ãë±Þ
```js
/(ab|cd)+|ef/
// ab ¶Ç´Â cd°¡ ÇÑ ¹ø ÀÌ»ó ¹Ýº¹µÇ´Â ¹®ÀÚ¿­ È¤Àº ¹®ÀÚ¿­ ef¿Í ¸ÅÄ¡
```
- °ýÈ£·Î µÑ·¯½ÎÀÎ ºÎºÐÀ» °°Àº Á¤±Ô Ç¥Çö½Ä ³»¿¡¼­ ÇØ´ç ºÎºÐÀ» ´Ù½Ã ÂüÁ¶ÇÒ ¼ö ÀÖ´Ù.
  - **\**¹®ÀÚ µÚ¿¡ ¼ýÀÚ¸¦ µÎ¸é µÈ´Ù.
```js
/['"][^'"]*['"]/
/*
ÀÛÀºµû¿ÈÇ¥³ª Å«µû¿ÈÇ¥ »çÀÌ¿¡ ÇÑ °³ ÀÌ»óÀÇ ¹®ÀÚ°¡ ÀÖ´Ù¸é ¸ÅÄ¡µÈ´Ù.
±×·¯³ª Ã³À½ µû¿ÈÇ¥¿Í ³¡ µû¿ÈÇ¥°¡ ¸ÅÄ¡µÉ ÇÊ¿ä´Â ¾ø´Ù.
Áï, µÎ µû¿ÈÇ¥ ¸ðµÎ ÀÛÀº µû¿ÈÇ¥ÀÌ°Å³ª Å« µû¿ÈÇ¥ÀÏ °ÍÀ» ¿ä±¸ÇÏÁö ¾Ê´Â´Ù.*/

//µû¿ÈÇ¥ÀÇ ¸ÅÄ¡¸¦ »ý°¢ÇÒ °æ¿ì °ýÈ£¸¦ »ç¿ëÇÑ´Ù.
/(['"])[^'"]*\1/
``` 

### ´ëÃ¼ Ç¥Çö½Ä, ±×·ìÈ­, ÂüÁ¶ ¹®ÀÚ
- **|** : ¿ÞÂÊ¿¡ ÀÖ´Â ºÎºÐ Ç¥Çö½ÄÀÌ³ª ¿À¸¥ÂÊ¿¡ ÀÖ´Â ºÎºÐ Ç¥Çö½Ä Áß ÇÏ³ª¸¦ ¸ÅÄ¡ÇÑ´Ù.
- **(...)** : ±×·ìÈ­ÇÏ¿© ´ÜÀ§·Î Ãë±Þ. ¶ÇÇÑ ³ªÁß¿¡ ÀÌ ±×·ìÀ» ÂüÁ¶ÇÒ ¼ö ÀÖµµ·Ï ±â¾ïÇÑ´Ù.
- **(?...)** : ±×·ìÈ­¸¸ ÇÑ´Ù. ³ªÁß¿¡ ÀÌ ±×·ìÀ» ÂüÁ¶ÇÒ ¼ö ÀÖµµ·Ï ±â¾ïÇÏÁö ¾Ê´Â´Ù.

### ¾ÞÄ¿ ¹®ÀÚ
- **^** : ¹®ÀÚ¿­ÀÇ ½ÃÀÛ ºÎºÐ, Á¤±Ô½ÄÀÌ ¿©·¯ ÁÙÀ» ´ë»óÀ¸·Î ÇÏ´Â °æ¿ì ÇÑ ÁÙÀÇ ½ÃÀÛ°ú ÀÏÄ¡
- **$** : ¹®ÀÚ¿­ÀÇ ³¡À» ³ªÅ¸³½´Ù. ¿©·¯ ÁÙÀÏ °æ¿ì ÇÑ ÁÙÀÇ ¸¶Áö¸·À» ³ªÅ¸³½´Ù.
- **\b** : ´Ü¾î °æ°è¸¦ ³ªÅ¸³½´Ù. Áï **\w**¿Í **\W** ¹®ÀÚ »çÀÌ, È¤Àº **\w** ¹®ÀÚ¿Í ¹®ÀÚ¿­ÀÇ ½ÃÀÛ ¶Ç´Â ³¡°ú ÀÏÄ¡ÇÑ´Ù.
- **\B** : ´Ü¾î °æ°è°¡ ¾Æ´Ñ °÷ÀÇ À§Ä¡¸¦ ³ªÅ¸³½´Ù.
- **(?=p)** : Àü¹æ Å½»ö ¼±¾ð
  - **(?=** ´ÙÀ½¿¡ ³ª¿À´Â ¹®ÀÚµéÀº ÆÐÅÏ p¿Í ¸ÅÄ¡µÇ¾î¾ß ÇÑ´Ù.
  - °Ë»öÇÑ °á°ú ÆÐÅÏµéÀÇ and ÀÇ¹Ì·Î ¸¹ÀÌ »ç¿ëµÇ´Â °Í °°À½.
  - (?=ÆÐÅÏ1)(?=ÆÐÅÏ2)(?=ÆÐÅÏ3)... : ÆÐÅÏ1, ÆÐÅÏ2, ÆÐÅÏ3À» ¸¸Á·ÇØ¾ß ÇÔ.
- **(?!p)** : ºÎÁ¤Çü Àü¹æ Å½»ö ¼±¾ð
  - **(?!** ´ÙÀ½¿¡ ³ª¿À´Â ¹®ÀÚµéÀº ÆÐÅÏ p¿Í ¸ÅÄ¡µÇÁö ¾Ê¾Æ¾ß ÇÑ´Ù.
```js
/\B[Ss]cript/
// JavaScript¿Í postscript¿¡ ¸ÅÄ¡ÇÑ´Ù.
// script³ª Scripting°ú´Â ¸ÅÄ¡ÇÏÁö ¾Ê´Â´Ù.
```
```js
/[Jj]ava([Ss]cript)?(?=\:)/
// JavaScript: asdfÀÇ JavaScript¿¡ ¸ÅÄ¡µÈ´Ù.
// Java in a NutshellÀÇ Java ºÎºÐÀº ÄÝ·ÐÀÌ ¾ø±â ¶§¹®¿¡ ¸ÅÄ¡µÇÁö ¾Ê´Â´Ù.
```

### ÇÃ·¡±×
- °íÂ÷¿ø ÆÐÅÏ ¸ÅÄª ±ÔÄ¢À» ÁöÁ¤ÇÑ´Ù.
- ´Ù¸¥ Á¤±Ô Ç¥Çö½Ä ¹®¹ý°ú´Â ´Þ¸® **/** ¹®ÀÚ ½Ö ¹Ù±ù¿¡, Áï µÎ ¹øÂ° ½½·¡½Ã ´ÙÀ½¿¡ µîÀåÇÑ´Ù.
- **i** : ´ë¼Ò¹®ÀÚ¸¦ ±¸º°ÇÏÁö ¾Ê´Â ¸ÅÄªÀ» ¼öÇàÇÑ´Ù.
- **g** : Àü¿ª ¸ÅÄªÀ» ¼öÇàÇÑ´Ù. Áï, Ã³À½ ¸ÅÄ¡¿¡¼­ ³¡³»Áö ¾Ê°í ¸ðµç ¸ÅÄ¡¸¦ Ã£´Â´Ù.
- **m** : ¿©·¯ ÁÙ ¸ðµå. **^**Àº ÁÙÀÇ ½ÃÀÛÀÌ³ª ¹®ÀÚ¿­ÀÇ ½ÃÀÛ°ú ÀÏÄ¡ÇÏ°í, **$**´Â ÁÙÀÇ ³¡ÀÌ³ª ¹®ÀÚ¿­ÀÇ ³¡°ú ÀÏÄ¡ÇÑ´Ù.
```js
/java$/im
// java¿Í ¸ÅÄªµÈ´Ù.
// Java\nis fun °ú ¸ÅÄªµÈ´Ù.
```

### ¹®ÀÚ¿­ ¸Þ¼­µå
- search()
  - Á¤±Ô Ç¥Çö½ÄÀ» ÀÎÀÚ·Î ¹Þ°í, °¡Àå Ã³À½ ¸ÅÄªµÇ´Â ºÎºÐ ¹®ÀÚ¿­ÀÇ À§Ä¡¸¦ ¹ÝÈ¯ÇÑ´Ù.
  - ¸ÅÄªµÇ´Â ºÎºÐ ¹®ÀÚ¿­ÀÌ ¾ø´Ù¸é -1À» ¹ÝÈ¯ÇÑ´Ù.
  - Àü¿ª °Ë»öÀ» Áö¿øÇÏÁö ¾Ê±â ¶§¹®¿¡, g ÇÃ·¡±×´Â ¹«½ÃµÈ´Ù.
```js
"JavaScript".search(/script/i); // 4 ¹ÝÈ¯
```
- replace()
  - Ã¹ ¹øÂ° ÀÎÀÚ·Î Á¤±Ô Ç¥Çö½ÄÀ» ¹Þ´Â´Ù.
  - µÎ ¹øÂ° ÀÎÀÚ·Î ±³Ã¼ÇÒ ¹®ÀÚ¿­À» ¹Þ´Â´Ù.
  - gÇÃ·¡±×°¡ ÀÖ´Ù¸é ¸ÅÄ¡µÇ´Â ¸ðµç ºÎºÐÀ» ¹Ù²Ù°í, ¾ø´Ù¸é °¡Àå Ã³À½ ¸ÅÄ¡µÇ´Â ºÎºÐ¸¸ ¹Ù²Û´Ù.
```js
text.replace(/javascript/gi, "JavaScript");
```
- match()
  - Á¤±Ô Ç¥Çö½Ä ÇÏ³ª¸¸À» ÀÎÀÚ·Î ¹Þ°í, ¸ÅÄ¡ °á°ú¸¦ ¹è¿­·Î ¹ÝÈ¯ÇÑ´Ù.
  - g ÇÃ·¡±×°¡ ¼³Á¤ µÇ¾î ÀÖÀ¸¸é, ¹®ÀÚ¿­ ³»ÀÇ ¸ðµç ¸ÅÄ¡ ºÎºÐÀ» ¹è¿­·Î ¹ÝÈ¯ÇÑ´Ù.
  - g ÇÃ·¡±×°¡ ¼³Á¤µÇ¾î ÀÖÁö ¾ÊÀ¸¸é, ¹è¿­ÀÇ Ã¹ ¹øÂ° ¿ä¼Ò¿¡ ¸ÅÄ¡µÈ ¹®ÀÚ¿­ÀÌ ¹ÝÈ¯µÇ°í, ³ª¸ÓÁö ¿ä¼ÒµéÀº Á¤±Ô Ç¥Çö½Ä »ó¿¡¼­ °í¶óÈ£·Î µÑ·¯½ÎÀÎ ºÎºÐ Ç¥Çö½Ä¿¡ ¸ÅÄ¡µÈ ¹®ÀÚ¿­ÀÌ ¹ÝÈ¯µÈ´Ù.
```js
"1 plus 2 equals 3".match(/\d+/g) // ["1","2","3"] ¹ÝÈ¯
```

### RegExp ¸Þ¼­µå
- exec()
  - ¾Õ¿¡¼­ StringÀÇ match() ¸Þ¼­µå¿Í ºñ½ÁÇÑµ¥, ÀÎÀÚ·Î ÆÐÅÏÀÌ ¾Æ´Ñ ¹®ÀÚ¿­À» ¹Þ´Â´Ù.
  - Áï, ÀÎÀÚ·Î ¹ÞÀº ¹®ÀÚ¿­¿¡ ´ëÇØ Á¤±Ô Ç¥Çö½ÄÀ» ½ÇÇàÇÑ´Ù.
  - ¸ÅÄ¡µÇ´Â ºÎºÐÀ» Ã£Áö ¸øÇÏ¸é nullÀ» ¹ÝÈ¯ÇÑ´Ù.
  - ³ª¸ÓÁö´Â match()¿Í ¸¶Âù°¡Áö·Î ¸ÅÄ¡µÈ ¹®ÀÚ¿­ÀÌ ÀúÀåµÇ°í, ³ª¸ÓÁö ¹è¿­ ¿ä¼Ò¿¡´Â °ýÈ£·Î µÑ·¯½ÎÀÎ ºÎºÐ Ç¥Çö½Ä¿¡ ¸ÅÄ¡µÇ´Â ¹®ÀÚ¿­ÀÌ ÀúÀåµÈ´Ù.
  - gÇÃ·¡±×°¡ ÀÖ´Â °æ¿ì lastIndex ÇÁ·ÎÆÛÆ¼¸¦ ÇöÀç ¸ÅÄ¡µÈ ºÎºÐ ¹®ÀÚ¿­ÀÇ ¹Ù·Î ´ÙÀ½ ¹®ÀÚ À§Ä¡·Î ¼³Á¤µÈ´Ù.
  - ÀÌÈÄ¿¡ ÇØ´ç ¸Þ¼­µå¸¦ ´Ù½Ã È£ÃâÇÏ¸é lastIndex ÇÁ·ÎÆÛÆ¼°¡ °¡¸®Å°´Â ¹®ÀÚ À§Ä¡ºÎÅÍ °Ë»öÀ» ½ÃÀÛÇÑ´Ù.
  - ÀÌ·¯ÇÑ Æ¯¼ö¼º ¶§¹®¿¡ ÇÑ ¹®ÀÚ¿­¿¡¼­ ¸ðµç Á¤±Ô Ç¥Çö½Ä ¸ÅÄªÀ» Ã£À¸·Á¸é exec()¸¦ ¿©·¯¹ø È£ÃâÇØ¾ß ÇÑ´Ù.
```js
    var pattern = /Java/g;
    var text = "JavaScript is more fun than Java!?";
    var res;
    while((res = pattern.exec(text)) != null){
        console.log(res[0] + "/position : " + res.index);
        // Java/position : 0
        // Java/position : 28
    }
```
- test()
  - ¹®ÀÚ¿­À» ÀÎÀÚ·Î ¹Þ°í Á¤±Ô Ç¥Çö½Ä¿Í ¸ÅÄ¡µÇ´Â ºÎºÐÀÌ ÀÖ´Ù¸é true¸¦ ¹ÝÈ¯ÇÑ´Ù.
  - test() ¸Þ¼­µå ¶ÇÇÑ g ÇÃ·¡±×¿¡ ´ëÇØ exec()¿Í °°Àº ¹æ½ÄÀ¸·Î µ¿ÀÛÇÑ´Ù.

### ÀÚÁÖ »ç¿ëµÇ´Â Á¤±ÔÇ¥Çö½Ä ¿¹Á¦µé
- ¿µ¹®¸¸ °¡´É
```js
var regExp = /^[a-zA-Z]+$/;
```
- ¿µ¹®°ú ¶ç¾î¾²±â¸¸ °¡´É
```js
var regExp = /^[a-zA-Z\s]+$/;
```
- ÇÑ±Û¸¸ °¡´É
```js
var regExp = /^[°¡-ÆR]+$/;
```
- ÇÚµåÆù¹øÈ£ Á¤±Ô Ç¥Çö½Ä
```js
var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
```
- ÀÏ¹Ý ÀüÈ­¹øÈ£ Á¤±Ô Ç¥Çö½Ä
```js
var regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
```
- id Ã¼Å© Á¤±Ô Ç¥Çö½Ä : ¼ýÀÚ¿Í ¿µ¹®¸¸ ÀÔ·Â °¡´É
```js
var regExp = /^[0-9a-z]+$/;
```
- ³¯Â¥ Á¤±Ô Ç¥Çö½Ä : 04/30/2019 È¤Àº 04/30/19
```js
var regExp = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
```
- jpg, gif ¶Ç´Â png È®ÀåÀÚ¸¦ °¡Áø ±×¸² ÆÄÀÏ¸í Á¤±Ô Ç¥Çö½Ä
```js
var regExp = /([^\s]+(?=\.(jpg|gif|png))\.\2)/;
```
- URL Á¤±Ô Ç¥Çö½Ä
```js
var regExp = /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/;
```
- ÀÌ¸ÞÀÏ Á¤±Ô Ç¥Çö½Ä
```js
var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
```
- Æ¯¼ö¹®ÀÚ, ¹®ÀÚ, ¼ýÀÚ Æ÷ÇÔ ÇüÅÂÀÇ 8~15ÀÚ¸® ÀÌ³»ÀÇ password Á¤±Ô Ç¥Çö½Ä
```js
var regex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
```
- match() ¸Þ¼­µå¸¦ ÀÌ¿ëÇØ url ÆÄ½Ì
```js
    var url = /(\w+):\/\/([\w.]+)\/(\S*)/;
    var text = "Visit my blog at http://www.example.com/~david";
    var res = text.match(url);
    if(res != null){
        var fullurl = res[0]; // http://www.example.com/~david
        var protocol = res[1]; // http
        var host = res[2]; // www.example.com
        var path = res[3]; // david
    }
```

### Á¤±Ô Ç¥Çö½ÄÀ» ÀÌ¿ëÇÑ º¸¾È
- ÀüÇüÀûÀÎ SQL Injection °ø°Ý
```js
var regExp = /\w*((\%27)|(\'))((\%6F)|o|(\%4F))((\%72)|r|(\%52))/ix;
```
- UNION Å°¿öµå¸¦ Æ÷ÇÔÇÑ SQL Injection
```js
var regExp = /((\%27)|(\'))union/ix;
```
- ÀüÇüÀûÀÎ XSS °ø°Ý
```js
var regExp = /((\%3C)|<)((\%2F)|\/)*[a-z0-9\%]+((\%3E)|>)/ix;
var regExp2 = /(\b)(on\S+)(\s*)=|javascript|(<\s*)(\/*)script/ig;
var regExp3 = /(\b)(on\S+)(\s*)=|javascript:|(<\s*)(\/*)script|style(\s*)=|(<\s*)meta/ig;
var regExp4 = /^(?!<!--)(?:<div>)?<+?\s*script\s*(?:\+|(?:\s|/)src=test.js)?>\s*(?:alert\(.*\);)?\s*(?:\/\/<)?<\/\s*script\s*>(?:<\/div>)?$/;
```
- ÀÌ¹ÌÁö ÅÂ±× »ðÀÔ XSS °ø°Ý
```js
var regExp = /((\%3C)|<)((\%69)|i|(\%49))((\%6D)|m|(\%4D))((\%67)|g|(\%47))[^\n]+((\%3E)|>)/I;
```
- Paranoid XSS °ø°Ý
```js
var regExp = /((\%3C)|<)[^\n]+((\%3E)|>)/I;
```
- Á¤±Ô Ç¥Çö½ÄÀ» »ç¿ëÇÏ¿© 100% Ãë¾àÁ¡À» ¸·À» ¼ö´Â ¾ø´Ù.
  - Á¤±Ô Ç¥Çö½ÄÀº »ç¿ëÀÚ°¡ ÀÔ·ÂÇÑ °ªÀÌ Á¦¾ÈµÈ Æû¿¡ ¸Â´Â Çü½ÄÀÎÁö Ã¼Å©ÇÏ´Â ¿ëµµ°¡ ÀûÇÕÇÏ´Ù. 
  ÆÐÅÏÀÇ ÀÏÄ¡ ¿©ºÎ¸¦ ÆÄ¾ÇÇÏ´Â Á¤µµ·Î »ç¿ëÇÏ°í, ÇÊÅÍ·Î »ç¿ëÇÏ±â¿¡´Â ºÎÁ·ÇÏ´Ù.
  SQL InjectionÀÌ³ª XSS¸¦ ¹æ¾îÇÏ´Â ¿ëµµ·Î´Â ÀûÇÕÇÏÁö ¾Ê´Ù.
  (°£´ÜÇÑ ÆÐÅÏÀº ¸·À» ¼ö ÀÖÁö¸¸, ¿Ïº®ÇÏ°Ô ¸·´Â°ÍÀº ºÒ°¡´É)
