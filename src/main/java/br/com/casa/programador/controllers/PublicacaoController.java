package br.com.casa.programador.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.casa.programador.models.Blocks;
import br.com.casa.programador.models.Publicacao;
import br.com.casa.programador.repository.PublicacaoRepository;

@Controller
@RequestMapping("/publicacao")
public class PublicacaoController {

	private Publicacao pub;

	@Autowired
	PublicacaoRepository pubRepository;

	public static String uploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imageUpload";

	@RequestMapping(value = "/publicacao", method = RequestMethod.GET)
	public String visualizarPublicacao(Model model) {
		Publicacao publicacao = pubRepository.findById(55).get();
		List<Blocks> conteudo = publicacao.getBlocks();
		for (Blocks c : conteudo) {
			System.out.println(c.getType());
		}
		model.addAttribute("conteudo", conteudo);
		return "publicacao/publicacao";
	}

	@ModelAttribute("publicacao")
	public Publicacao publicacao() {
		return new Publicacao();
	}

	@PostMapping("/salvarEdicao")
	public String criarJson(@RequestBody String json) {
		Gson gson = new Gson();
		Publicacao pub = gson.fromJson(json, Publicacao.class);
		for (Blocks b : pub.getBlocks()) {
			b.getData().setBlock(b);
			b.setPub(pub);
		}
		this.pub = pub;
		return "redirect:/editor/editar";
	}

	@RequestMapping(value = "/salvarPublicacao", method = RequestMethod.POST)
	public String salvarPublicacao(@RequestParam("imagemGrande") MultipartFile imagemGrande, 
			@RequestParam("imagemPequena") MultipartFile imagemPequena,
			@RequestParam("nomeArtigo") String nomeArtigo,
			RedirectAttributes mensagem) {
		System.out.println("passou");
		String imagemGrandeS = salvarArquivo(imagemGrande);
		String imagemPequenaS = salvarArquivo(imagemPequena);
		if (this.pub.equals(null)) {
			mensagem.addFlashAttribute("mensagemFail", "publicacao não pode ser salva =(, por favor contate um administrador caso persista");
		}else{
			this.pub.setImageGrandeUrl(uploadDirectory + "\\" + imagemGrandeS);
			this.pub.setImagemPequenaUrl(uploadDirectory + "\\" + imagemPequenaS);
			this.pub.setNome(nomeArtigo);
			pubRepository.save(this.pub);
			this.pub = null;
			mensagem.addFlashAttribute("mensagem", "publicacao salva com sucesso!");
		}
		return "redirect:/editor/editar";
	}

	@RequestMapping(value = "/salvarPublicacao", method = RequestMethod.GET)
	public String salvarPublicacao() {
		return "paginaEdicao/salvarEdicao";
	}

	private String salvarArquivo(MultipartFile file) {
		StringBuilder fileName = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getOriginalFilename();
	}

}
